package company.tap.tapcardinputkit.internal.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.StrictMode
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
import java.io.InputStream
import java.net.URL


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
    private lateinit var cardBrand: String
    private var result: List<String>? = null
    private var image: Drawable? = null

    @SuppressLint("SetTextI18n")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val policy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        this.afterTextChanged {
            cardBrandType()
            formValueChangeListener?.numberValueChanged(
                editableText.toString(),
                isCardValid(editableText.toString()),
                tapCard.cardObject
            )
            validateEditText()
        }

    }

    private fun validateEditText() {
        if (editableText.trim().length <= 6 || (CardValidator.validate(editableText.toString()).validationState == CardValidationState.invalid)) {
            error = resources.getString(R.string.card_number_invalid)
        } else {
            error = "null"
            image?.let {
                println("bmp is $it")
                this.setCompoundDrawablesWithIntrinsicBounds(null, null, it, null)
            }
        }
    }

    private fun cardBrandType() {
        val brand = validateCardNumber(editableText.toString())
        if (brand.cardBrand != null) {
            cardBrand = brand.cardBrand.name
        }
        val inputlength = editableText.toString().length
        tapCard = TapCard()
        getSavedURL(cardBrand)
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
            )
            val pos = editableText.length
            setSelection(pos)
        }
        count = editableText.toString().length
        if (::tapCard.isInitialized && brand.cardBrand != null) {
            tapCard.cardObject = brand.cardBrand.name
        }
    }

    private fun getSavedURL(cardBrand: String) {
        val sharedPref = context?.getSharedPreferences("App", Context.MODE_PRIVATE)
        val imagePaths = sharedPref?.getString("imagepath", null)
        result = imagePaths?.split(",")!!.map { it.trim() }
        var imageURL: String? = null
        when {
            cardBrand.equals(CardBrand.knet.name) -> {
                imageURL = result!!.elementAt(0)
            }
            cardBrand.equals(CardBrand.americanExpress.name) -> {
                imageURL = result!!.elementAt(1)
            }
            cardBrand.equals(CardBrand.benefit.name) -> {
                imageURL = result!!.elementAt(2)
            }
            cardBrand.equals(CardBrand.mada.name) -> {
                imageURL = result!!.elementAt(3)
            }
            cardBrand.equals(CardBrand.visa.name) -> {
                imageURL = result!!.elementAt(4)
            }
            cardBrand.equals(CardBrand.masterCard.name) -> {
                imageURL = result!!.elementAt(5)
            }
            cardBrand.equals(CardBrand.fawry.name) -> {
                imageURL = result!!.elementAt(6)
            }
            cardBrand.equals(CardBrand.omanNet.name) -> {
                imageURL = result!!.elementAt(7)
            }
            cardBrand.equals(CardBrand.tap.name) -> {
                imageURL = result!!.elementAt(8)
            }
        }

        loadImageFromWebUrl(imageURL)
    }

    private fun loadImageFromWebUrl(url: String?) {
        var newURL: String? = null
        if (url != null) {
            if (url.contains("[")) {
                newURL = url!!.replace("[", "")
            } else if (url.contains("]")) {
                newURL = url.replace("]", "")
            } else {
                newURL = url
            }
            image = try {
                val iStream = URL(newURL).content as InputStream
                Drawable.createFromStream(iStream, "src name")

            } catch (e: Exception) {
                null
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









