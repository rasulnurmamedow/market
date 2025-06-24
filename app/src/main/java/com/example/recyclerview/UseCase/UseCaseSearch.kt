package com.example.recyclerview.UseCase

import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.interfaces.Resource
import com.example.recyclerview.repositoryes.Repository
import javax.inject.Inject

class UseCaseSearch @Inject constructor(val repository: Repository) {
    suspend fun getProductsSearch(filter: String): Resource<List<Product>>{
        return repository.namefilter(filter)
    }
}