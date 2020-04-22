package company.tap.tapcardinputkit.internal.views

import android.content.Context
import company.tap.tapcardinputkit.internal.OnFormValueChangeListener
import company.tap.tapuilibrary.TapSwitch

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardSaveSwitch(context: Context) : TapSwitch(context) {
    var formValueChangeListener: OnFormValueChangeListener? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.setOnCheckedChangeListener { _, isChecked ->
            formValueChangeListener?.saveCardSwitched(isChecked)
        }
    }
}