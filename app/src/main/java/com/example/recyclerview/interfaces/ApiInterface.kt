package com.example.recyclerview.interfaces

import com.example.recyclerview.datamodels.Banners
import com.example.recyclerview.datamodels.Categories
import com.example.recyclerview.datamodels.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/banners")
    suspend fun getBanners(
    ): Response<List<Banners>>
    @GET("api/categories")
    suspend fun getCategories(
    ):Response<List<Categories>>

    @GET("api/products/category/{id}/")
    suspend fun getProduct(
        @Path("id") id: Int,
        @Query("page") page: Int
    ):Response<List<Product>>

    @GET("api/products/search/")
    suspend fun searchProducts(
        @Query("name") name: String,
    ): Response<List<Product>>



}