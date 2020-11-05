package com.shindra.arrakis.webservice

import androidx.viewbinding.BuildConfig
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.shindra.arrakis.ArrakisApplication
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


abstract class RetrofitServices<Service> {

    protected val service by lazy { service() }

    open fun okHttpBuilder() =
        OkHttpClient.Builder().addInterceptor(ChuckerInterceptor(ArrakisApplication.arrakisContext))

    open fun retrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(getUrl())
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpBuilder().build())
    }

    open fun retrofit() = retrofitBuilder().build()
    open fun service() = retrofit().create(apiServiceClass())

    abstract fun apiServiceClass(): Class<Service>
    abstract fun getUrl(): String

}