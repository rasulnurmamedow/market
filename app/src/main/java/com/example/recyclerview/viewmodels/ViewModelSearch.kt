package com.example.recyclerview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerview.UseCase.UseCaseProducts
import com.example.recyclerview.UseCase.UseCaseSearch
import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.interfaces.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSearch @Inject constructor(val useCase: UseCaseSearch): ViewModel() {



    var isLoading: Boolean = false

    private val _products = MutableLiveData< List<Product>>()
    val products: LiveData<List<Product>> = _products
    private val _state = MutableLiveData< ResponseState>()

    fun searchProducts(filter: String){
        if (isLoading == true) return
        isLoading = true
        _state.postValue(ResponseState.Loading)


        viewModelScope.launch(Dispatchers.IO) {

            try {
                when(val fetchedProducts = useCase.getProductsSearch(filter)){
                    is Resource.Success ->{
                        _products.postValue(fetchedProducts.data)

                        _state.postValue(ResponseState.Success)
                    }

                    is Resource.Error -> {

                        _state.postValue(ResponseState.Error(null))


                    }
                }
            }
            catch (e: Exception){
                _state.postValue(ResponseState.Error(null))
            }
            finally {
                isLoading = false
            }



        }
    }
}