package company.tap.tapcardinputkit.open

import company.tap.commonmodels.TapCard

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
interface TapCardInputListener {
    fun onCardScanClicked()
    fun onValueChanged(card: TapCard)
    fun saveCardSwitched(checked: Boolean)
}