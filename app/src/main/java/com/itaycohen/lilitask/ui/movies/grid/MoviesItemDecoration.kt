package com.itaycohen.lilitask.ui.movies.grid

import android.content.Context
import android.graphics.Rect
import android.util.LayoutDirection
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Adds interior dividers to a RecyclerView with a GridLayoutManager.
 */
class MoviesItemDecoration(
    private val mItemOffset: Int,
    private val columnsSize: Int
) : RecyclerView.ItemDecoration() {

    constructor(
        context: Context,
        @DimenRes itemOffsetId: Int,
        columnsSize: Int
    ) : this(
        context.resources.getDimensionPixelSize(itemOffsetId),
        columnsSize
    )

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val lp = view.layoutParams as GridLayoutManager.LayoutParams
        val columnPosition = lp.spanIndex
        val isLTR = view.resources.configuration.layoutDirection == LayoutDirection.LTR
        val isStartColumn = columnPosition == 0
        val isEndColumn = columnPosition == columnsSize-1
        val isTopRow = lp.bindingAdapterPosition < columnsSize
        val isBottomRow =
            if (parent.adapter == null)
                true
            else
                lp.bindingAdapterPosition > parent.adapter!!.itemCount - columnsSize

        val left = if (isLTR)
            if (isStartColumn) mItemOffset else mItemOffset/2
        else
            if (isEndColumn) mItemOffset else mItemOffset/2
        val top = if (isTopRow) mItemOffset else mItemOffset/2
        val right = if (isLTR)
            if (isEndColumn) mItemOffset else mItemOffset/2
        else
            if (isStartColumn) mItemOffset else mItemOffset/2
        val bottom = if (isBottomRow) mItemOffset else mItemOffset/2
        outRect.set(left, top, right, bottom)
    }
}