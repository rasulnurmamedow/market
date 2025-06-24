package com.example.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.databinding.CategoryitemBinding
import com.example.recyclerview.datamodels.Categories
import com.example.recyclerview.interfaces.CategoryCallback


class CategoriesAdapter(val callback: CategoryCallback): RecyclerView.Adapter<CategoriesAdapter.ItemHolder>() {
        private var itemsList = ArrayList<Categories>()




        inner class ItemHolder(item: View, val callback: CategoryCallback): RecyclerView.ViewHolder(item), View.OnClickListener {
            val binding = CategoryitemBinding.bind(item)
            private var model: Categories? = null

            init {
                binding.categoryName.setOnClickListener(this)
            }
            fun bind(item: Categories) = with(binding){
                model = item
                categoryName.text = item.name
            }

            override fun onClick(v: View?) {
                if(v?.id ==  binding.categoryName.id){
                    model?.let {
                        callback.onItemPassed(it.id)
                    }
                }
            }
        }
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ItemHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.categoryitem,parent,false)
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




        fun submitList(list: List<Categories>) {

            itemsList.clear()
            itemsList.addAll(list)
            notifyDataSetChanged()
        }


    }