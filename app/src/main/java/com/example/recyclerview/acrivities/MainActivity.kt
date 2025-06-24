package com.example.recyclerview.acrivities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recyclerview.R
import com.example.recyclerview.adapters.ViewpagerAdapter
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.fragments.BannerFragment
import com.example.recyclerview.fragments.BlankFragment
import com.example.recyclerview.fragments.CardFragment
import com.example.recyclerview.fragments.ProductDetailFragment
import com.example.recyclerview.fragments.SearchFragment
import com.example.recyclerview.interfaces.ItemCallback
import com.example.recyclerview.viewmodels.ViewModelCard
import com.example.recyclerview.viewmodels.ViewModelMain
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),ItemCallback {
    lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModelMain by viewModels()
    private val lisfrag2: List<Fragment> = listOf(
        BlankFragment(this),
        SearchFragment(this),
        CardFragment()
    )
    val lisfrag = listOf(
        BannerFragment.newInstance(R.drawable.banner1),
        BannerFragment.newInstance(R.drawable.banner2)
    )

    private val vpadapter = ViewpagerAdapter(this, lisfrag)
    private val vpadapter2 = ViewpagerAdapter(this, lisfrag2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager2.adapter = vpadapter
        binding.Categories.adapter = vpadapter2
        TabLayoutMediator(binding.tabLayout2, binding.Categories) { tab, position ->
            tab.text = viewModel.tabtitles[position]
        }.attach()

    }

    override fun onItemPassed(model: Product) {
        supportFragmentManager.beginTransaction().replace(R.id.fraame, ProductDetailFragment(model)).commit()
    }


}