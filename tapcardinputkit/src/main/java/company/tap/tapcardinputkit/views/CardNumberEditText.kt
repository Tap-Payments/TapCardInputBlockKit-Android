package company.tap.tapcardinputkit.views

import android.content.Context
import android.util.AttributeSet
import company.tap.tapcardinputkit.OnFormValueChangeListener
import tapuilibrarykotlin.TapEditText

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class CardNumberEditText(context: Context, attrs: AttributeSet) : TapEditText(context, attrs) {
    var formValueChangeListener: OnFormValueChangeListener? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.afterTextChanged {
            formValueChangeListener?.cvvValueChanged(it)
        }
    }

}