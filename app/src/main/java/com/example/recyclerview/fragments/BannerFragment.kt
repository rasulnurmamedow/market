package com.example.recyclerview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recyclerview.databinding.FragmentBannerBinding

class BannerFragment() : Fragment() {

    private var binding2: FragmentBannerBinding? = null

    companion object {
        private const val ARG_IMAGE_ID = "image_id"

        fun newInstance(imageId: Int): BannerFragment {
            val fragment = BannerFragment()
            val args = Bundle()
            args.putInt(ARG_IMAGE_ID, imageId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding2 = FragmentBannerBinding.inflate(inflater, container, false)
        return binding2?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем imageId из аргументов
        val imageId = arguments?.getInt(ARG_IMAGE_ID, -1) ?: -1

        if (imageId != -1) { // Проверяем, что imageId был передан
            binding2?.imageView2?.setImageResource(imageId)
        } else {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding2 = null
    }
}