package company.tap.tapcardinputkit.internal.views

import android.content.Context
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import company.tap.commonmodels.TapCard
import company.tap.tapcardinputkit.internal.OnFormValueChangeListener
import company.tap.tapcardvalidator_android.CardBrand
import company.tap.tapcardvalidator_android.CardBrand.americanExpress
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
    lateinit var cardNumber:SpannableStringBuilder

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.afterTextChanged {
            val brand: DefinedCardBrand = validateCardNumber(it)
            println("brand obatined is ${brand.cardBrand}")
            val spacings: IntArray
            if(!brand.cardBrand.name.equals(americanExpress)) {
                spacings = intArrayOf(4, 8, 12)
            } else {
                spacings = intArrayOf(4, 10)
            }

            var text: String = it
            text = text.replace(" ", "")
             cardNumber = SpannableStringBuilder(text)

            for (i in spacings.indices.reversed()) {
                val space = spacings[i]
                if (space < text.length) {
                    cardNumber.insert(space, " ")
                }
            }
            println("cardNumber after spacing is $cardNumber")

            cardType = brand.cardBrand
            formValueChangeListener?.numberValueChanged(cardNumber.toString(), isCardValid(it), cardType)

        }
    }

    private fun isCardValid(cardNumber:String) : Boolean {
        val brand = CardValidator.validate(cardNumber)
        println("brand cardValid ${brand.cardBrand}")
        return validateCardNumber(cardNumber).validationState == CardValidationState.valid

    }

    private fun validateCardNumber(cardNumber: String) : DefinedCardBrand =
        CardValidator.validate(cardNumber.replace(" ", ""))



}