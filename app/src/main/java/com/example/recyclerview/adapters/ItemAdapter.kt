package com.example.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.datamodels.Product
import com.example.recyclerview.R
import com.example.recyclerview.databinding.ParitemBinding
import com.example.recyclerview.interfaces.ItemCallback
import coil.load

class ItemAdapter(val callback: ItemCallback): RecyclerView.Adapter<ItemAdapter.ItemHolder>() {
    private var itemsList = ArrayList<Product>()




    inner class ItemHolder(item: View, val callback: ItemCallback): RecyclerView.ViewHolder(item), View.OnClickListener {
        val binding = ParitemBinding.bind(item)
        private var model: Product? = null

        init {
            binding.container.setOnClickListener(this)
        }
        fun bind(item: Product) = with(binding){
            model = item
            textView.text = item.name
            price.text = "${item.price} TMT"
            category.text =item.category.name
            img.load(item.image) {
                crossfade(true)
                crossfade(1000)
            }
        }

        override fun onClick(v: View?) {
            if(v?.id ==  binding.container.id){
                model?.let {
                    callback.onItemPassed(it)
                }
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.paritem,parent,false)
        return ItemHolder(view, callback)
    }

    override fun onBindViewHolder(
        holder: ItemHolder,
        position: Int
    ) {
        holder.bind(itemsList[position])
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }




    fun submitList(list: List<Product>) {

        //itemsList.clear()
        itemsList.addAll(list)
        notifyDataSetChanged()
    }
    fun clear(){
        itemsList.clear()
        notifyDataSetChanged()
    }

}