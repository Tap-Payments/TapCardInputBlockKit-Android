package company.tap.tapcardinputsample

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by AhlaamK on 4/22/20.

Copyright (c) 2020    Tap Payments.
All rights reserved.
 **/
object RetrofitClient {
    var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient: OkHttpClient? =
                getOkHttpClient()
            retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit

    }

    private fun getOkHttpClient(): OkHttpClient? {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        // add application interceptor to httpClientBuilder
        httpClientBuilder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer " + "sk_test_kovrMB0mupFJXfNZWx6Etg5y"
                )
                .addHeader("Application", "company.tap.goSellSDKExample")
                .addHeader("Accept", "application/json")
                .addHeader("content-typ", "application/json").build()
            chain.proceed(request)
        })
        httpClientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(if (!BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BODY))
        return httpClientBuilder.build()
    }
}