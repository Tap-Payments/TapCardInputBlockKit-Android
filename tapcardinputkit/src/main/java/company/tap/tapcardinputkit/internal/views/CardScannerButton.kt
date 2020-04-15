package company.tap.tapcardinputkit.internal.views

import android.content.Context
import android.util.AttributeSet
import company.tap.tapcardinputkit.internal.OnFormValueChangeListener
import tapuilibrarykotlin.TapButton

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardScannerButton(context: Context, attributeSet: AttributeSet): TapButton(context, attributeSet) {
    var formValueChangeListener: OnFormValueChangeListener? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.setOnClickListener {
            formValueChangeListener?.scanButtonClicked()
        }
    }
}