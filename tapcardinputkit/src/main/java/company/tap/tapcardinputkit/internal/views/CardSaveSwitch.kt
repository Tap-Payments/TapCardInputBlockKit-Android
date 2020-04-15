package company.tap.tapcardinputkit.internal.views

import android.content.Context
import android.util.AttributeSet
import company.tap.tapcardinputkit.internal.OnFormValueChangeListener
import tapuilibrarykotlin.TapSwitch

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardSaveSwitch(context: Context, attrs: AttributeSet) : TapSwitch(context, attrs) {
    var formValueChangeListener: OnFormValueChangeListener? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.setOnCheckedChangeListener { _, isChecked ->
            formValueChangeListener?.saveCardSwitched(isChecked)
        }
    }
}