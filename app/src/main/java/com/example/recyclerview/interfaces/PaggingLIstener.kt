package com.example.recyclerview.interfaces

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class PaggingLIstener(private val layoutManager: GridLayoutManager,
                           private val onLoadMore: () -> Unit
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (dy > 0 && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount - THRESHOLD_ITEM_COUNT
            && firstVisibleItemPosition >= 0) {
            onLoadMore.invoke()
        }
    }

    companion object {
        private const val THRESHOLD_ITEM_COUNT = 2 // Load when 5 items from the end
    }
}