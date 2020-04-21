package company.tap.tapcardinputkit.internal.views

import android.content.Context
import android.util.AttributeSet
import company.tap.tapcardinputkit.R
import company.tap.tapcardinputkit.internal.OnFormValueChangeListener
import tapuilibrarykotlin.TapEditText

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardCvvEditText(context: Context, attrs: AttributeSet) : TapEditText(context, attrs) {
    var formValueChangeListener: OnFormValueChangeListener? = null
    var inputString = 0
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.afterTextChanged {
            inputString = it.length
            if (isCvvValid()) {
                formValueChangeListener?.cvvValueChanged(it, isCvvValid())
            }
            if(editableText.isEmpty()){
                error = resources.getString(R.string.cvv_invalid)
            }else{
                error = "null"
            }
        }
    }

    private fun isCvvValid(): Boolean {
        return inputString == editableText.length
    }
}