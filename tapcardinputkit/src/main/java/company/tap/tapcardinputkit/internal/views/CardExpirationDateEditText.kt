package company.tap.tapcardinputkit.internal.views

import android.content.Context
import android.text.Editable
import android.text.Spanned
import android.view.View
import android.view.inputmethod.EditorInfo
import company.tap.tapcardinputkit.R
import company.tap.tapcardinputkit.internal.OnFormValueChangeListener
import company.tap.tapcardinputkit.internal.validators.DateValidator
import company.tap.tapcardinputkit.internal.validators.SlashSpan
import company.tap.tapuilibrary.TapEditText

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardExpirationDateEditText(context: Context) :
    TapEditText(context) {
    var formValueChangeListener: OnFormValueChangeListener? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.afterTextChanged {
            if (editableText.length == 1 && Character.getNumericValue(editableText.get(0)) >= 2) {
                prependLeadingZero(editableText)
            }
            val paddingSpans: Array<Any> = arrayOf(
                editableText.getSpans(
                    0, editableText.length,
                    SlashSpan::class.java
                )
            )
            for (span in paddingSpans) {
                editableText.removeSpan(span)
            }
            addDateSlash(editableText)
            if ((selectionStart == 4 && !editableText.toString()
                    .endsWith("20") || selectionStart == 6) && isDateValid()
            ) {
                focusNextView()
                error = "null"
            } else {
                error = resources.getString(R.string.date_invalid)
            }
            formValueChangeListener?.dateValueChanged(it, isDateValid())
        }
    }

    private fun isDateValid(): Boolean {
        return DateValidator.isValidDate(getMonth(), getYear())
    }

    private fun addDateSlash(editableText: Editable) {
        val index = 2
        val length: Int = editableText.length
        if (index <= length) {
            editableText.setSpan(
                SlashSpan(), index - 1, index,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    private fun prependLeadingZero(editableText: Editable) {
        val firstChar: Char = editableText[0]
        editableText.replace(0, 1, "0").append(firstChar)
    }

    /**
     * Gets month.
     *
     * @return the 2-digit month, formatted with a leading zero if necessary. If no month has been specified, an empty string is returned.
     */
    private fun getMonth(): String {
        val string: String = getString()
        return if (string.length < 2) {
            ""
        } else getString().substring(0, 2)
    }

    /**
     * Gets year.
     *
     * @return the 2- or 4-digit year depending on user input. If no year has been specified, an empty string is returned.
     */
    private fun getYear(): String {
        val string: String = getString()
        return if (string.length == 4 || string.length == 6) {
            getString().substring(2)
        } else ""
    }

    /**
     * Convenience method to get the input text as a [String].
     */
    private fun getString(): String {
        val editableText = text
        return editableText?.toString() ?: ""
    }

    /**
     * Request focus for the next view.
     *
     * @return the view
     */
    private fun focusNextView(): View? {
        if (imeActionId == EditorInfo.IME_ACTION_GO) {
            return null
        }
        val next: View = try {
            focusSearch(View.FOCUS_RIGHT)
        } catch (e: IllegalArgumentException) {
            focusSearch(View.FOCUS_DOWN)
        }
        return if (next.requestFocus()) {
            next
        } else null
    }

}

