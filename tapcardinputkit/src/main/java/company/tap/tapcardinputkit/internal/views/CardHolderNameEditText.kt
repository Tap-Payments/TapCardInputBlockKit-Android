package company.tap.tapcardinputkit.internal.views

import android.content.Context
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.text.InputFilter.LengthFilter
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.util.AttributeSet
import company.tap.tapcardinputkit.R
import company.tap.tapcardinputkit.internal.OnFormValueChangeListener
import kotlinx.android.synthetic.main.tap_card_input.view.*
import tapuilibrarykotlin.TapEditText


/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardHolderNameEditText(context: Context, attrs: AttributeSet) : TapEditText(context, attrs) {

    var formValueChangeListener: OnFormValueChangeListener? = null
    private var NAME_ON_CARD_MAX_LENGTH = 26
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.afterTextChanged {
            filters = arrayOf(AllCaps(),
                LengthFilter(NAME_ON_CARD_MAX_LENGTH),
                object : InputFilter {
                    override fun filter(
                        source: CharSequence,
                        start: Int,
                        end: Int,
                        dest: Spanned,
                        dest_start: Int,
                        dest_end: Int
                    ): CharSequence {
                        var keepOriginal = true
                        val sb = StringBuilder(end - start)
                        for (i in start until end) {
                            val c = source[i]
                            if (isCharAllowed(c)) // put your condition here
                                sb.append(c) else keepOriginal = false
                        }
                        return if (source is Spanned && !keepOriginal) {
                            val sp = SpannableString(sb)
                            TextUtils.copySpansFrom(source, start, sb.length, null, sp, 0)
                            sp
                        } else {
                            sb
                        }
                    }

                    private fun isCharAllowed(c: Char): Boolean {
                        return Character.isLetter(c) || Character.isSpaceChar(c) || isDotChar(c)
                    }

                    private fun isDotChar(c: Char): Boolean {
                        return c == '.'
                    }
                })
            if (isCardholdernameValid()) {
                formValueChangeListener?.nameValueChanged(it, isCardholdernameValid())
                error = "null"
            } else {
                error = resources.getString(R.string.card_holder_name_invalid)
            }

        }
    }

    private fun isCardholdernameValid(): Boolean {
        return editableText.trim().length >= 3
    }

}