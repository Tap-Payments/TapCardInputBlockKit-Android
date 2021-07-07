package company.tap.tapcardinputkit.open

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.text.InputFilter
import android.util.AttributeSet
import android.widget.LinearLayout
import company.tap.commonmodels.CardBrand
import company.tap.commonmodels.TapCard
import company.tap.tapcardinputkit.R
import company.tap.tapcardinputkit.internal.OnFormValueChangeListener
import kotlinx.android.synthetic.main.tap_card_input.view.*


/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
 class TapCardInput(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs),
    OnFormValueChangeListener {

    var tapCardInputListener: TapCardInputListener? = null
    val tapCard = TapCard()
    var maxInput = 0


    init {
        inflate(
            context,
            R.layout.tap_card_input2, this
        )
        card_cvv.formValueChangeListener = this
       // card_scanner.formValueChangeListener = this
        card_number.formValueChangeListener = this
        save_card.formValueChangeListener = this
        holder_name.formValueChangeListener = this
        expiration_date.formValueChangeListener = this

    }

    override fun scanButtonClicked() {
        tapCardInputListener?.onCardScanClicked()
    }

    override fun cvvValueChanged(cvv: String, valid: Boolean) {
        if (valid)
            if (tapCard.cardObject == CardBrand.americanExpress.name) {
                maxInput = 4
            } else {
                maxInput = 3
            }
        card_cvv.filters = arrayOf(InputFilter.LengthFilter(maxInput))

    }

    override fun dateValueChanged(date: String, valid: Boolean) {
        if (valid) {
            tapCard.expiry = date

            tapCard.expMonth = date.take(2)
            tapCard.expYear = date.takeLast(2)

            tapCardInputListener?.onValueChanged(tapCard)
        }
    }

    override fun nameValueChanged(name: String, valid: Boolean) {
        println("name tap val = [${name}], valid = [${valid}]")
        if (valid) {
            tapCard.name = name
            tapCardInputListener?.onValueChanged(tapCard)
        }
    }

    override fun numberValueChanged(number: String, valid: Boolean, cardType: String?) {
        if (valid) {
            println("number tap val = [${number}], valid = [${valid}], cardType = [${cardType}]")
            if (number.isNotEmpty()) {
                tapCard.firstSix = number.replace(" ", "").take(6)
            }
            if (number.isNotEmpty()) {
                tapCard.lastFour = number.trim().takeLast(4)
            }
            tapCard.cardObject = cardType
            tapCardInputListener?.onValueChanged(tapCard)
        }
    }

    override fun saveCardSwitched(checked: Boolean) {
        tapCardInputListener?.saveCardSwitched(checked)
    }



}