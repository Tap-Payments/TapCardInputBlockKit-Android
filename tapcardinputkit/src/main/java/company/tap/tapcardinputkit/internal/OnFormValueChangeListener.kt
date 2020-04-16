package company.tap.tapcardinputkit.internal

import company.tap.tapcardvalidator_android.CardBrand

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
interface OnFormValueChangeListener {
    fun scanButtonClicked()
    fun cvvValueChanged(cvv: String, valid: Boolean)
    fun dateValueChanged(date: String, valid: Boolean)
    fun nameValueChanged(name: String, valid: Boolean)
    fun numberValueChanged(number: String, valid: Boolean,cardType:CardBrand)
    fun saveCardSwitched(checked: Boolean)
}