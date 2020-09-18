package com.leon.mvpkotlinframe.serviceapi

import com.leon.mvpkotlinframe.model.LoginResponseBean
import com.leon.networklib.bean.NetResponseBaseWrapperBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 服务器的接口调用api
 *
 * Rjava+Retrofit
 */
interface ServiceAPI {
    @POST("/user/login")
    @FormUrlEncoded
    fun login(@Field("username")userName:String,
                 @Field("password")password:String)
            :Observable<NetResponseBaseWrapperBean<LoginResponseBean>>
}