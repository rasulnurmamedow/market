package com.example.recyclerview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerview.UseCase.UseCaseSearch
import com.example.recyclerview.adapters.ItemAdapter
import com.example.recyclerview.databinding.FragmentSearchBinding
import com.example.recyclerview.interfaces.ItemCallback
import com.example.recyclerview.viewmodels.ViewModelProducts
import com.example.recyclerview.viewmodels.ViewModelSearch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class SearchFragment(callback: ItemCallback) : Fragment() {

    private val viewModel: ViewModelSearch by viewModels()
    private var binding: FragmentSearchBinding? = null
    private val adapter = ItemAdapter(callback)




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.rc?.layoutManager = GridLayoutManager(context, 2)
        binding?.rc?.adapter = adapter
        viewModel.products.observe(viewLifecycleOwner) { data -> adapter.submitList(data)}
        binding?.search?.setOnQueryTextListener( object :SearchView.OnQueryTextListener{


            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.clear()
                viewModel.searchProducts(query.toString())


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
    //            adapter.submitList(repo.namefilter(newText.toString()))
                return true
            }
        })



    }


}