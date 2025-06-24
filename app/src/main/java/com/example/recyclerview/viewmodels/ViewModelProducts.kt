package com.example.recyclerview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerview.UseCase.UseCaseGetCategories
import com.example.recyclerview.UseCase.UseCaseProducts
import com.example.recyclerview.datamodels.Categories
import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.interfaces.ErrorResponse
import com.example.recyclerview.interfaces.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelProducts @Inject constructor(
    private val useCase: UseCaseProducts,
    private val useCaseCategories: UseCaseGetCategories
) : ViewModel() {
    var currentPage :Int = 1
    var currentCategory : Int = 1



    var isLoading: Boolean = false


    private val _products = MutableLiveData< List<Product>>()
    val products: LiveData<List<Product>> = _products
    private val _state = MutableLiveData< ResponseState>()
    val state: LiveData<ResponseState> = _state

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> = _categories


    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fetchCategories = useCaseCategories.getCategories()
                when(fetchCategories){
                    is Resource.Error -> {

                    }
                    is Resource.Success<*> -> {
                        _categories.postValue(fetchCategories.data as List<Categories>?)
                    }
                }
            }
            catch (e: Exception){

            }
            _state.postValue(ResponseState.Error(null))


        }
    }

    fun getProducts(filter: Int,page: Int) {
        if (isLoading == true) return
        isLoading = true
        _state.postValue(ResponseState.Loading)


        viewModelScope.launch(Dispatchers.IO) {


            try {
                when(val fetchedProducts = useCase.getProductsFilter(filter, page)){
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
                if (currentPage > 1){currentPage--}
                _state.postValue(ResponseState.Error(null))
            }
            finally {
                isLoading = false
            }



        }
    }
}

sealed class ResponseState{
    data object Success : ResponseState()
    class Error(val errorResponse: ErrorResponse?) : ResponseState()
    data object Loading : ResponseState()
}