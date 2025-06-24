package com.example.recyclerview.viewmodels

import androidx.lifecycle.ViewModel
import com.example.recyclerview.UseCase.UseCaseCardclass
import com.example.recyclerview.UseCase.UseCaseGetPrice
import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.datamodels.ToralPrice
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelCard @Inject constructor(private val useCase: UseCaseCardclass,private val useCasePrice: UseCaseGetPrice ): ViewModel() {


    var totalPrice: ToralPrice = ToralPrice(0f,0)
    var sales = mutableListOf<Product>()
    fun getSales(){
        sales = useCase.getSalesProducts() as MutableList<Product>
        totalPrice= useCasePrice.getPrice()
    }


}