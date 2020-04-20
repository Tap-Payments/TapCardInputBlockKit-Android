package company.tap.tapcardinputkit.internal.views

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import company.tap.commonmodels.TapCard
import company.tap.tapcardinputkit.internal.OnFormValueChangeListener
import company.tap.tapcardvalidator_android.CardBrand
import company.tap.tapcardvalidator_android.CardValidator
import kotlinx.android.synthetic.main.tap_card_input.view.*

import tapuilibrarykotlin.TapEditText

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardCvvEditText(context: Context, attrs: AttributeSet) : TapEditText(context, attrs) {
    var formValueChangeListener: OnFormValueChangeListener? = null
    var inputString =0
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.afterTextChanged {
            inputString = it.length
            formValueChangeListener?.cvvValueChanged(it, isCvvValid())
        }
    }

    private fun isCvvValid(): Boolean {
        return inputString == editableText.length
    }
}