package com.yes.inmyfood

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A fragment representing a list of Items.
 */
class ImageCollectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_collection, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                adapter =  ImageCollectRecyclerViewAdapter(Array(12) {"1"})

                val spacing = (resources.getDimension(R.dimen.recyclerview_grid_spacing) / resources.displayMetrics.density).toInt()
                setPadding(spacing, spacing, spacing, spacing)

                clipToPadding = false
                clipChildren = false

                addItemDecoration(object: RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        outRect.set(spacing, spacing, spacing, spacing)
                    }
                })
            }
        }
        return view
    }
}