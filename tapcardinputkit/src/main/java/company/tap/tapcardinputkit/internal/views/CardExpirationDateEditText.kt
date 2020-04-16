package company.tap.tapcardinputkit.internal.views

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import company.tap.tapcardinputkit.internal.OnFormValueChangeListener
import kotlinx.android.synthetic.main.tap_card_input.view.*
import tapuilibrarykotlin.TapEditText
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardExpirationDateEditText(context: Context, attrs: AttributeSet) : TapEditText(context, attrs) {
    var formValueChangeListener: OnFormValueChangeListener? = null
    var isSlash = false
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.afterTextChanged {
            dateValidator(it)
            formValueChangeListener?.dateValueChanged(it, true)
        }
    }


    private fun dateValidator(input: String) {
        val mLastInput = ""
        val formatter = SimpleDateFormat("MM/yy", Locale.ENGLISH)
        val expiryDateDate = Calendar.getInstance()
        try {
            expiryDateDate.time = formatter.parse(input)
        } catch (e:Exception) {
            if (input.length == 2 && !mLastInput.endsWith("/") && isSlash) {
                isSlash = false
                val month = Integer.parseInt(input)
                if (month <= 12) {
                    expiration_date.setText(expiration_date.text.toString().substring(0, 1))
                    expiration_date.setSelection(expiration_date.text.toString().length)
                } else {
                    expiration_date.clearComposingText()
                    expiration_date.setText("")
                    expiration_date.setSelection(expiration_date.text.toString().length)
                    Toast.makeText(context.applicationContext, "Enter a valid month", Toast.LENGTH_LONG).show()
                }
            }else if (input.length == 2 && !mLastInput.endsWith("/") && !isSlash) {
                isSlash = true
                val month = Integer.parseInt(input)
                if (month <= 12) {
                    expiration_date.setText(expiration_date.text.toString() + "/")
                    expiration_date.setSelection(expiration_date.text.toString().length)
                }else if(month > 12){
                    expiration_date.setText("")
                    expiration_date.setSelection(expiration_date.text.toString().length)
                    expiration_date.clearComposingText()

                }


            }else if (input.length == 1) {

                val month: Int = Integer.parseInt(input)
                if (month in 2..11) {
                    isSlash = true
                    expiration_date.setText("""0${expiration_date.text.toString()}/""")
                    expiration_date.setSelection(expiration_date.text.toString().length)
                }
            }
        }
        }


}

