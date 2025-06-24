package com.example.recyclerview.UseCase

import com.example.recyclerview.datamodels.Categories
import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.interfaces.Resource
import com.example.recyclerview.repositoryes.Repository
import javax.inject.Inject

class UseCaseGetCategories@Inject constructor(val repository: Repository) {
    suspend fun getCategories(): Resource<List<Categories>>{
        return repository.fetchCategories()
    }

}