package company.tap.tapcardinputkit

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
interface OnFormValueChangeListener {
    fun scanButtonClicked()
    fun cvvValueChanged(cvv: String)
    fun dateValueChanged(date: String)
    fun nameValueChanged(name: String)
    fun numberValueChanged(number: String)
    fun saveCardSwitched(checked: Boolean)
}