package com.yes.inmyfood.ui.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yes.inmyfood.R

class PostingAddFragment : Fragment() {

    companion object {
        fun newInstance() = PostingAddFragment()
    }

    private lateinit var viewModel: PostingAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posting_add_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostingAddViewModel::class.java)
        // TODO: Use the ViewModel
    }

}