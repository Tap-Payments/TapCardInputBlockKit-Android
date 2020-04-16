package company.tap.tapcardinputkit.open

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import  company.tap.tapcardvalidator_android.CardBrand
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

    init {
        inflate(context,
            R.layout.tap_card_input, this)
        card_cvv.formValueChangeListener = this
        card_scanner.formValueChangeListener = this
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
            println("cvv not impelmented = [${cvv}], valid = [${valid}]")

    }

    override fun dateValueChanged(date: String, valid: Boolean) {
        if (valid) {
            tapCard.expiry = date
            if(date.contains("/")){
                tapCard.expMonth= date.take(2)
                tapCard.expYear = date.takeLast(2)
            }
            tapCardInputListener?.onValueChanged(tapCard)
        }
    }

    override fun nameValueChanged(name: String, valid: Boolean) {
        if (valid) {
            tapCard.name = name
            tapCardInputListener?.onValueChanged(tapCard)
        }
    }

    override fun numberValueChanged(number: String, valid: Boolean, cardType:CardBrand) {
        if (valid) {
            if (number.isNotEmpty()){
                tapCard.firstSix = number.replace(" ","").take(6)
            }
            if (number.isNotEmpty()){
                tapCard.lastFour = number.trim().takeLast(4)
            }
            tapCard.brand?.name
            tapCardInputListener?.onValueChanged(tapCard)
        }
    }

    override fun saveCardSwitched(checked: Boolean) {
        tapCardInputListener?.saveCardSwitched(checked)
    }

}