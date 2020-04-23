package company.tap.tapcardinputsample

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import company.tap.commonmodels.TapCard
import company.tap.tapcardinputkit.open.TapCardInputListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(),
    TapCardInputListener {
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        card_input.tapCardInputListener = this
        var mAPIService: APIService? = null

        // println("size of response $size")
        val sp: SharedPreferences = getSharedPreferences(
            "AppSharedPref",
            0
        ) // Open SharedPreferences with name AppSharedPref
        //After oncreate

        mAPIService = ApiUtils.apiService

        //Some Button click

        var json: JSONObject = JSONObject()
        json.put("transaction_mode", "PURCHASE")
        json.put("items", "")
        json.put("shipping", "")
        json.put("taxes", "")
        json.put("customer", "")
        json.put("currency", "KWD")
        json.put("total_amount", "10")
        json.put("merchant_id", null)
        json.put("payment_type", "ALL")

        println("json = [${json}]")

        val list: MutableList<String> = ArrayList()
        mAPIService.getPaymentOptions(json)?.enqueue(object : Callback<PaymentResponse> {
            override fun onResponse(
                call: Call<PaymentResponse>,
                response: Response<PaymentResponse>
            ) {

                val paymentList = response.body()?.payment_methods ?: emptyList()
                val sharedPref =
                    baseContext?.getSharedPreferences("App", Context.MODE_PRIVATE) ?: return

                for (i in paymentList.indices) {
                    val imageIndex: String = paymentList[i].image.toString()
                    list.add(imageIndex)
                    with(sharedPref.edit()) {
                        putString("imagepath",list.toString())
                        apply()
                    }

                }
                println("image ste $list")

            }

            override fun onFailure(call: Call<PaymentResponse>, t: Throwable) {
                Log.i(TAG, "throw$t")
            }

        })


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
