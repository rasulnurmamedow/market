package com.example.recyclerview.UseCase

import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.interfaces.Resource
import com.example.recyclerview.repositoryes.Repository
import javax.inject.Inject

class UseCaseProducts @Inject constructor(val repository: Repository) {

    suspend fun getProductsFilter(filter: Int,page: Int): Resource<List<Product>>{
        return repository.categoryfilter(filter,page)
    }

}