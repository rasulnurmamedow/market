package com.example.recyclerview.UseCase

import com.example.recyclerview.datamodels.ToralPrice
import com.example.recyclerview.repositoryes.Repository
import javax.inject.Inject

class UseCaseGetPrice @Inject constructor(val repository: Repository) {
    fun getPrice():ToralPrice{
        return repository.getPrices()
    }
}