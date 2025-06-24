package com.example.recyclerview.repositoryes

import com.example.recyclerview.R
import com.example.recyclerview.datamodels.Banners
import com.example.recyclerview.datamodels.Categories
import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.datamodels.ToralPrice
import com.example.recyclerview.interfaces.ApiInterface
import com.example.recyclerview.interfaces.NetworkResultHandler
import com.example.recyclerview.interfaces.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository@Inject constructor(private val api: ApiInterface) {
    val base = listOf(
        null
    )


    val sale = mutableListOf<Product>()
    suspend fun categoryfilter(query: Int,page: Int): Resource<List<Product>> {
        val response = api.getProduct(query,page)
        val result = response.body()
        return try {
            if (response.isSuccessful && result!=null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message(), NetworkResultHandler.getErrorResponse(response.errorBody()!!.string()))
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }


    suspend fun fetchCategories(): Resource<List<Categories>>{
        val response = api.getCategories()
        val result = response.body()
        return try {
            if (response.isSuccessful && result!=null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message(), NetworkResultHandler.getErrorResponse(response.errorBody()!!.string()))
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }
    suspend fun fetchBanners(): Resource<List<Banners>>{
        val response = api.getBanners()
        val result = response.body()
        return try {
            if (response.isSuccessful && result!=null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message(), NetworkResultHandler.getErrorResponse(response.errorBody()!!.string()))
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }

    fun getSales():List<Product>{
        return sale
    }
    suspend fun namefilter(query: String): Resource<List<Product>> {
        val response = api.searchProducts(query)
        val result = response.body()
        return try {
            if (response.isSuccessful && result!=null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message(), NetworkResultHandler.getErrorResponse(response.errorBody()!!.string()))
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }
    fun getPrices(): ToralPrice{
        val sump = sale.sumOf { it.price.toDouble() }
        return ToralPrice(sump.toFloat(),sale.size)
    }
    fun cancelSales(){
        sale.clear()
    }
    fun confirmSales(){
        sale.clear()
    }

}