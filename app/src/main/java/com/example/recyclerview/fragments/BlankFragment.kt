package com.example.recyclerview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.adapters.CategoriesAdapter
import com.example.recyclerview.adapters.ItemAdapter
import com.example.recyclerview.databinding.FragmentBlankBinding
import com.example.recyclerview.interfaces.CategoryCallback
import com.example.recyclerview.interfaces.ItemCallback
import com.example.recyclerview.interfaces.PaggingLIstener
import com.example.recyclerview.viewmodels.ResponseState
import com.example.recyclerview.viewmodels.ViewModelProducts
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class BlankFragment(callback: ItemCallback) : Fragment(), CategoryCallback {
    private var binding: FragmentBlankBinding? = null
    private val adapter = ItemAdapter(callback)
    private val categoryAdapter = CategoriesAdapter(this)

    private val viewModel: ViewModelProducts by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBinding.inflate(inflater)

        return binding?.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding?.rc?.layoutManager = gridLayoutManager
        binding?.rc?.adapter = adapter
        binding?.category?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        binding?.category?.adapter = categoryAdapter
        viewModel.categories.observe(viewLifecycleOwner) { data -> categoryAdapter.submitList(data)}
        viewModel.products.observe(viewLifecycleOwner) { data -> adapter.submitList(data) }

        viewModel.state.observe(viewLifecycleOwner) { data ->
            if (viewModel.currentPage == 1){
                when(data){

                    is ResponseState.Success ->{
                        binding?.error?.visibility = View.INVISIBLE
                        binding?.rc?.visibility = View.VISIBLE
                        binding?.loading?.visibility = View.INVISIBLE}
                    is ResponseState.Error -> {
                        binding?.error?.visibility = View.VISIBLE
                        binding?.rc?.visibility = View.INVISIBLE
                        binding?.loading?.visibility = View.INVISIBLE}
                    is ResponseState.Loading -> {
                        binding?.error?.visibility = View.INVISIBLE
                        binding?.rc?.visibility = View.INVISIBLE
                        binding?.loading?.visibility = View.VISIBLE
                    }
                }
            }


        }


        viewModel.getCategories()
        viewModel.getProducts(viewModel.currentCategory,viewModel.currentPage)

        binding?.rc?.addOnScrollListener(object : PaggingLIstener(gridLayoutManager,
            onLoadMore = {
                if (viewModel.isLoading == false){
                    viewModel.currentPage++
                    viewModel.getProducts(viewModel.currentCategory,viewModel.currentPage)
                }


            }
        ){}
        )
    }



    override fun onDestroyView() {
        super.onDestroyView()

    }

    override fun onItemPassed(model: Int) {
        viewModel.currentCategory = model
        viewModel.currentPage = 1
        adapter.clear()
        viewModel.getProducts(viewModel.currentCategory,viewModel.currentPage)

    }

}