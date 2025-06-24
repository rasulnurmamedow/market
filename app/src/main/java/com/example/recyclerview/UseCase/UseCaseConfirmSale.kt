package com.example.recyclerview.UseCase

import com.example.recyclerview.repositoryes.Repository
import javax.inject.Inject

class UseCaseConfirmSale@Inject constructor(val repository: Repository) {
    fun confirmSale(){
        repository.confirmSales()
    }
}