package com.example.recyclerview.UseCase

import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.repositoryes.Repository
import javax.inject.Inject

class UseCaseCardclass @Inject constructor(val repository: Repository) {
    fun getSalesProducts(): List<Product>{
        return repository.getSales()
    }

}