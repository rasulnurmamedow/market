package com.example.recyclerview.UseCase

import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.repositoryes.Repository
import javax.inject.Inject

class UseCaseProductDetail @Inject constructor(val repository: Repository) {

    fun addSale(product: Product){
        repository.sale.add(product)

    }

}