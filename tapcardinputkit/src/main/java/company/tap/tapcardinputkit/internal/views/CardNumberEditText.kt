package company.tap.tapcardinputkit.internal.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import company.tap.tapcardinputkit.internal.OnFormValueChangeListener
import company.tap.tapcardvalidator_android.CardBrand
import company.tap.tapcardvalidator_android.CardValidationState
import company.tap.tapcardvalidator_android.CardValidator
import company.tap.tapcardvalidator_android.DefinedCardBrand
import tapuilibrarykotlin.TapEditText


/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardNumberEditText(context: Context, attrs: AttributeSet) : TapEditText(context, attrs) {
    var formValueChangeListener: OnFormValueChangeListener? = null
    lateinit var cardType: CardBrand
    var count = 0
    lateinit var spaceArray: IntArray

    @SuppressLint("SetTextI18n")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.afterTextChanged {
            val brand = validateCardNumber(editableText.toString())
            val cardBrand = brand?.cardBrand.name
            val inputlength = editableText.toString().length

            spaceArray = if (brand == CardBrand.americanExpress) {
                intArrayOf(4, 10)
            } else {
                intArrayOf(4, 9, 14)
            }
            if (count <= inputlength && (inputlength in spaceArray)) {
                setText("$editableText ");
                val pos = editableText.length
                setSelection(pos)
            } else if (count >= inputlength && (inputlength in spaceArray)) {
                setText(
                    editableText.toString()
                        .substring(
                            0, editableText
                                .toString().length - 1
                        )
                );
                val pos = editableText.length
                setSelection(pos)
            }
            count = editableText.toString().length
            cardType = brand.cardBrand
            formValueChangeListener?.numberValueChanged(editableText.toString(), isCardValid(editableText.toString()), cardType)
        }


    }

}

private fun isCardValid(cardNumber: String): Boolean {
    println("cardNumber in valid = [${cardNumber}]")
    return validateCardNumber(cardNumber).validationState == CardValidationState.valid

}

private fun validateCardNumber(cardNumber: String): DefinedCardBrand =
    CardValidator.validate(cardNumber.replace(" ", ""))


