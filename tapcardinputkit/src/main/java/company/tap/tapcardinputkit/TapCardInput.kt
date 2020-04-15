package company.tap.tapcardinputkit

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.tap_card_input.view.*

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class TapCardInput(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), OnFormValueChangeListener {

    init {
        inflate(context, R.layout.tap_card_input, this)
        card_cvv.formValueChangeListener = this
        card_scanner.formValueChangeListener = this
        card_number.formValueChangeListener = this
        save_card.formValueChangeListener = this
        holder_name.formValueChangeListener = this
        expiration_date.formValueChangeListener = this
    }

    override fun scanButtonClicked() {
        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun cvvValueChanged(cvv: String) {
        Toast.makeText(context, cvv, Toast.LENGTH_SHORT).show()
    }

    override fun dateValueChanged(date: String) {
        Toast.makeText(context, date, Toast.LENGTH_SHORT).show()
    }

    override fun nameValueChanged(name: String) {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
    }

    override fun numberValueChanged(number: String) {
        Toast.makeText(context, number, Toast.LENGTH_SHORT).show()
    }

    override fun saveCardSwitched(checked: Boolean) {
        Toast.makeText(context, "$checked", Toast.LENGTH_SHORT).show()
    }

}