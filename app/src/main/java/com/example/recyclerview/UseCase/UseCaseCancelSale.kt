package com.example.recyclerview.UseCase

import com.example.recyclerview.repositoryes.Repository
import javax.inject.Inject

class UseCaseCancelSale @Inject constructor(val repository: Repository) {
    fun cancelSale(){
        repository.cancelSales()
    }
}