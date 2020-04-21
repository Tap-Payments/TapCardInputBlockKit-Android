package company.tap.tapcardinputkit.internal.views

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import company.tap.commonmodels.TapCard
import company.tap.tapcardinputkit.R
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
    lateinit var tapCard: TapCard
    var count = 0
    lateinit var spaceArray: IntArray
    var maxLength: Int = 0
    private lateinit var cardBrand:String
    @SuppressLint("SetTextI18n")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.afterTextChanged {
            val brand = validateCardNumber(editableText.toString())
            if(brand.cardBrand != null){
                 cardBrand = brand.cardBrand.name
            }

            val inputlength = editableText.toString().length
            tapCard = TapCard()
            if (cardBrand == CardBrand.americanExpress.name) {
                spaceArray = intArrayOf(4, 10)
                maxLength = 18
            } else {
                spaceArray = intArrayOf(4, 9, 14)
                maxLength = 19
            }
            filters = arrayOf(InputFilter.LengthFilter(maxLength))
            if (count <= inputlength && (inputlength in spaceArray)) {
                setText("$editableText ")
                val pos = editableText.length
                setSelection(pos)
            } else if (count >= inputlength && (inputlength in spaceArray)) {
                setText(
                    editableText.toString()
                        .substring(0, editableText.toString().length - 1)
                );
                val pos = editableText.length
                setSelection(pos)
            }
            count = editableText.toString().length
            if (::tapCard.isInitialized && brand.cardBrand!=null) {
                tapCard.cardObject = brand.cardBrand.name
            }

            formValueChangeListener?.numberValueChanged(
                editableText.toString(),
                isCardValid(editableText.toString()),
                tapCard.cardObject
            )
            if (!isCardValid(editableText.toString())) {
                error = resources.getString(R.string.card_number_invalid)
            }
        }


    }

}

private fun isCardValid(cardNumber: String): Boolean {
    println("cardNumber in valid = [${cardNumber}]")
    return validateCardNumber(cardNumber).validationState == CardValidationState.valid

}

private fun validateCardNumber(cardNumber: String): DefinedCardBrand =
    CardValidator.validate(cardNumber.replace(" ", ""))


