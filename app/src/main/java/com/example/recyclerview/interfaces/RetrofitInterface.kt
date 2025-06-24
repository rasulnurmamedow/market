package com.example.recyclerview.interfaces

import android.annotation.SuppressLint
import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("StaticFieldLeak")
object RetrofitInterface{

private lateinit var context: Context

fun initialize(context: Context) {
    this.context = context
}

private val headerInterceptor = Interceptor { chain ->
    val originalRequest: Request = chain.request()

    val modifiedRequest = originalRequest.newBuilder()
        .header("Content-Type", "application/json")
        .build()
    chain.proceed(modifiedRequest)
}



val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(headerInterceptor)
    .build()

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("http://192.168.246.123:8080/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
}