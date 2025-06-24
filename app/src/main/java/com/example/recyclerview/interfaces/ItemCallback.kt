package com.example.recyclerview.interfaces

import com.example.recyclerview.datamodels.Product

interface ItemCallback {
    fun onItemPassed(model: Product)
}

