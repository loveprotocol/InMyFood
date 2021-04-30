package com.yes.inmyfood

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.yes.inmyfood.databinding.FragmentPostingListBinding

/**
 * A fragment representing a list of Items.
 */
class PostingListFragment : Fragment() {

    companion object {
        fun newInstance() = PostingListFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this).get(PostingListViewModel::class.java) }
    private lateinit var viewDataBinding: FragmentPostingListBinding
    private lateinit var activity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentPostingListBinding.inflate(layoutInflater, container, false)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.viewmodel = viewModel
        viewDataBinding.fragPostingListRv.layoutManager = CenterZoomLayoutManager(context, RecyclerView.HORIZONTAL, false)
        viewDataBinding.fragPostingListRv.adapter = PostingListViewAdapter()
        viewDataBinding.fragPostingListRv.addItemDecoration(FirstEndItemDecoration(activity))

        val dummyList: ArrayList<Posting> = arrayListOf()
        activity
        dummyList.add(Posting(JodaTimeHelper.getCurrentTimeStamp(),
            JodaTimeHelper.convertTimeStamp("2021/01/22 12:00:00"),
            ContextCompat.getDrawable(activity, R.drawable.k1)!!, arrayListOf("tag1", "tag2", "tag3")))
        dummyList.add(Posting(JodaTimeHelper.getCurrentTimeStamp(),
            JodaTimeHelper.convertTimeStamp("2021/01/22 12:00:00"),
            ContextCompat.getDrawable(activity, R.drawable.k2)!!, arrayListOf("tag1", "tag2", "tag3")))
        dummyList.add(Posting(JodaTimeHelper.getCurrentTimeStamp(),
            JodaTimeHelper.convertTimeStamp("2021/01/22 12:00:00"),
            ContextCompat.getDrawable(activity, R.drawable.k3)!!, arrayListOf("tag1", "tag2", "tag3")))
        dummyList.add(Posting(JodaTimeHelper.getCurrentTimeStamp(),
            JodaTimeHelper.convertTimeStamp("2021/01/22 12:00:00"),
            ContextCompat.getDrawable(activity, R.drawable.k4)!!, arrayListOf("tag1", "tag2", "tag3")))
        dummyList.add(Posting(JodaTimeHelper.getCurrentTimeStamp(),
            JodaTimeHelper.convertTimeStamp("2021/01/22 12:00:00"),
            ContextCompat.getDrawable(activity, R.drawable.k5)!!, arrayListOf("tag1", "tag2", "tag3")))
        dummyList.add(Posting(JodaTimeHelper.getCurrentTimeStamp(),
            JodaTimeHelper.convertTimeStamp("2021/01/22 12:00:00"),
            ContextCompat.getDrawable(activity, R.drawable.k6)!!, arrayListOf("tag1", "tag2", "tag3")))
        dummyList.add(Posting(JodaTimeHelper.getCurrentTimeStamp(),
            JodaTimeHelper.convertTimeStamp("2021/01/22 12:00:00"),
            ContextCompat.getDrawable(activity, R.drawable.k7)!!, arrayListOf("tag1", "tag2", "tag3")))
        dummyList.add(Posting(JodaTimeHelper.getCurrentTimeStamp(),
            JodaTimeHelper.convertTimeStamp("2021/01/22 12:00:00"),
            ContextCompat.getDrawable(activity, R.drawable.k8)!!, arrayListOf("tag1", "tag2", "tag3")))
        dummyList.add(Posting(JodaTimeHelper.getCurrentTimeStamp(),
            JodaTimeHelper.convertTimeStamp("2021/01/22 12:00:00"),
            ContextCompat.getDrawable(activity, R.drawable.k9)!!, arrayListOf("tag1", "tag2", "tag3")))
        dummyList.add(Posting(JodaTimeHelper.getCurrentTimeStamp(),
            JodaTimeHelper.convertTimeStamp("2021/01/22 12:00:00"),
            ContextCompat.getDrawable(activity, R.drawable.k10)!!, arrayListOf("tag1", "tag2", "tag3")))

        viewModel.start(dummyList)
    }
}