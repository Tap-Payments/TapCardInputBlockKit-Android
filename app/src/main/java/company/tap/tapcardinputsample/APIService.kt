package company.tap.tapcardinputsample

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by AhlaamK on 4/22/20.

Copyright (c) 2020    Tap Payments.
All rights reserved.
 **/
interface APIService {

    @POST("payment/types")
    fun getPaymentOptions(@Body paymentOptionsRequest: JSONObject?): Call<PaymentResponse>
}


//**App Utils**

object ApiUtils {

    val BASE_URL = "https://api.tap.company/v2/"

    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL)!!.create(APIService::class.java)

}