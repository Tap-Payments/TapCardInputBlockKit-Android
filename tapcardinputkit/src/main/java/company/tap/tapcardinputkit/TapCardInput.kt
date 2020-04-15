package company.tap.tapcardinputkit

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import company.tap.tapcardinputkit.views.*

/**
 *
 * Created by Mario Gamal on 4/15/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 *
 */
class TapCardInput(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.tap_card_input, this)

        val cardCvv : CardCvvEditText = findViewById(R.id.card_cvv)
        val saveCard : CardSaveSwitch = findViewById(R.id.save_card)
        val cardNumber : CardNumberEditText = findViewById(R.id.card_number)
        val cardScanner : CardScannerButton = findViewById(R.id.card_scanner)
        val holderName : CardHolderNameEditText = findViewById(R.id.holder_name)
        val expDate : CardExpirationDateEditText = findViewById(R.id.expiration_date)

    }
}