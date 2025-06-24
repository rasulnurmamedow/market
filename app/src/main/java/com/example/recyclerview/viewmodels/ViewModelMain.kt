package com.example.recyclerview.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.recyclerview.R
import com.example.recyclerview.fragments.BannerFragment
import com.example.recyclerview.fragments.BlankFragment
import com.example.recyclerview.fragments.CardFragment
import com.example.recyclerview.fragments.SearchFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelMain@Inject constructor(): ViewModel() {


    val tabtitles = listOf<String>(
        "Products",
        "Search",
        "Card"
    )
}