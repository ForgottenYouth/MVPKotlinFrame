package com.leon.networklib

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 网络请求的入口
 */
class NetWorkClient {

    val serviceUrl:String = "https://www.wanandroid.com"

    private object Holder{
        var INSTANCE= NetWorkClient()
    }

    companion object{
       var instance = Holder.INSTANCE
    }

    fun <T> instanceRetrofit(apiInterface:Class<T>):T{
        //OKHttp3
        var httpClient:OkHttpClient = OkHttpClient().newBuilder()
            .readTimeout(3000,TimeUnit.SECONDS)
            .connectTimeout(3000,TimeUnit.SECONDS)
            .writeTimeout(3000,TimeUnit.SECONDS)
            .build()

        var retrofit:Retrofit = Retrofit.Builder()
            .baseUrl(serviceUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return retrofit.create(apiInterface)
    }

}