package company.tap.tapcardinputsample

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by AhlaamK on 4/22/20.

Copyright (c) 2020    Tap Payments.
All rights reserved.
 **/
class PaymentResponse : Serializable {

    @SerializedName("id")
    val id: String? = null

    @SerializedName("object")
    val ids: String? = null

    @SerializedName("payment_methods")
    var payment_methods: List<PaymentMethod>? = null

    @SerializedName("api_version")
    val api_version: String? = null

    class PaymentMethod : Serializable {
        @SerializedName("id")
        val id: String? = null

        @SerializedName("source_id")
        val source_id: String? = null

        @SerializedName("name")
        val name: String? = null

        @SerializedName("image")
        val image: String? = null

        @SerializedName("payment_type")
        val payment_type: String? = null

        @SerializedName("supported_card_brands")
        val supported_card_brands: Array<SupportCard>? = null

        @SerializedName("extra_fees")
        var extra_fees: ArrayList<ExtraFee>? = null

        @SerializedName("supported_currencies")
        val supported_currencies: Array<SupportCurrency>? = null

        @SerializedName("order_by")
        val order_by: String? = null

        @SerializedName("cc_markup")
        val cc_markup: Float? = null

        @SerializedName("asynchronous")
        val asynchronous: Boolean? = false

        @SerializedName("threeDS")
        val threeDS: Boolean? = false
    }

    class ExtraFee : Serializable {
        @SerializedName("currency")
        val currency: String? = null

        @SerializedName("minimum_fee")
        val minimum_fee: String? = null

        @SerializedName("maximum_fee")
        var maximum_fee: String? = null

        @SerializedName("type")
        var type: String? = null

        @SerializedName("value")
        var value: Double? = 0.00

    }

}

enum class SupportCurrency {

}

enum class SupportCard {

}







