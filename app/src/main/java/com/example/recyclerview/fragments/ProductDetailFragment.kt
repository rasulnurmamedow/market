package com.example.recyclerview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import coil.load
import com.example.recyclerview.UseCase.UseCaseProductDetail
import com.example.recyclerview.databinding.FragmentProductDetailBinding
import com.example.recyclerview.datamodels.Product
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailFragment(val product: Product) : Fragment() {
    var binding: FragmentProductDetailBinding? = null
    @Inject
    lateinit var useCase: UseCaseProductDetail




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater)

        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding?.imageView4?.load(product.image)
            binding?.name?.text = product.name
            binding?.price?.text = "${product.price} TMT"
            binding?.category?.text = product.category.name
            binding?.description?.text = product.description
            binding?.button2?.setOnClickListener {
                useCase.addSale(product)
                onDestroy()
            }
        val callbackL = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                onDestroy()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callbackL)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().apply {
            remove(this@ProductDetailFragment) // Удаляем текущий фрагмент
        }.commit()
    }
}