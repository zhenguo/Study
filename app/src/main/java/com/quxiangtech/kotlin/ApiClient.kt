package com.quxiangtech.kotlin

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    object SingletonHolder {
//        val INSTANCE = ApiClient()
    }

    companion object {

//        val instance = SingletonHolder.INSTANCE

        fun testStaticFun() {

        }
    }

    fun request() {
        LoadingDialog.cancel()
//        println(instance.hashCode())
    }

    fun <T> initRetrofit(apiInterface: Class<T>): T {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(apiInterface)
    }
}