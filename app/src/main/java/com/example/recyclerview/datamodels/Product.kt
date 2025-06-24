package com.example.recyclerview.datamodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Product(
    val name: String,
    val price: Float,
    val image: String,
    val category: Categories,
    val description: String
)

data class ToralPrice(
    val totalPrice: Float,
    val size: Int
)

data class Categories(
    val id: Int,
    val name: String
)
data class Banners(
    val title: String,
    val image: String
)
