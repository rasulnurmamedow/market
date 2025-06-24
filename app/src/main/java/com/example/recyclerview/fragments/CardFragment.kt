package com.example.recyclerview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerview.UseCase.UseCaseCancelSale
import com.example.recyclerview.UseCase.UseCaseCardclass
import com.example.recyclerview.UseCase.UseCaseConfirmSale
import com.example.recyclerview.adapters.ItemAdapter
import com.example.recyclerview.databinding.FragmentCarzinaBinding
import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.interfaces.ItemCallback
import com.example.recyclerview.viewmodels.ViewModelCard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class CardFragment : Fragment(), ItemCallback {
    private val adapter = ItemAdapter(this)
    private val viewModel: ViewModelCard by viewModels()
    @Inject
    lateinit var useCaseConfirmSale: UseCaseConfirmSale
    @Inject
    lateinit var useCaseCancelSale: UseCaseCancelSale


    var binding: FragmentCarzinaBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarzinaBinding.inflate(inflater)

        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding?.rc?.layoutManager = GridLayoutManager(context, 2)
        binding?.rc?.adapter = adapter
        binding?.button?.setOnClickListener {
            val alertDialogBuy = AlertDialog.Builder(requireContext())
            alertDialogBuy.setTitle("Confirm buy")
            alertDialogBuy.setMessage("Are you sure you want to buy this?")
            alertDialogBuy.setPositiveButton("Yes") { dialog,w ->
                useCaseConfirmSale.confirmSale()
                adapter.clear()
                viewModel.getSales()
                adapter.submitList(viewModel.sales)
                binding?.textView8?.text = viewModel.totalPrice.totalPrice.toString()
                binding?.textView9?.text = viewModel.totalPrice.size.toString()
                Toast.makeText(context, "Purchase confirmed", Toast.LENGTH_LONG).show()

            }
            alertDialogBuy.setNegativeButton("No") { dialog,w ->


                Toast.makeText(context, "Purchase canceled", Toast.LENGTH_LONG).show()

            }
            alertDialogBuy.create().show()
        }

    }

    override fun onItemPassed(model: Product) {


    }

    override fun onResume() {
        super.onResume()
        adapter.clear()
        viewModel.getSales()
        binding?.textView8?.text = viewModel.totalPrice.totalPrice.toString()
        binding?.textView9?.text = viewModel.totalPrice.size.toString()


        adapter.submitList(viewModel.sales)

    }
}