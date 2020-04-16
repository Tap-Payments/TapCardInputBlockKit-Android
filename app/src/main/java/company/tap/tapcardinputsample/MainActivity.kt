package company.tap.tapcardinputsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import company.tap.commonmodels.TapCard
import company.tap.tapcardinputkit.open.TapCardInput
import company.tap.tapcardinputkit.open.TapCardInputListener
import kotlinx.android.synthetic.main.activity_main.*


 class MainActivity : AppCompatActivity(),
    TapCardInputListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        card_input.tapCardInputListener = this
    }

    override fun onCardScanClicked() {
        Toast.makeText(this, "Activity Received", Toast.LENGTH_SHORT).show()
    }

     override fun saveCardSwitched(checked: Boolean) {

     }

     override fun onValueChanged(card: TapCard) {
         println("tapcard value in main = [${card}]")
     }

 }
