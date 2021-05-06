package com.yes.inmyfood.postings

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.yes.inmyfood.*
import com.yes.inmyfood.data.Posting
import com.yes.inmyfood.databinding.FragmentPostingsBinding
import com.yes.inmyfood.util.ZoomOutSidePageTransformer
import com.yes.inmyfood.util.JodaTimeHelper

/**
 * A fragment representing a list of Items.
 */
class PostingsFragment : Fragment() {

    companion object {
        fun newInstance() = PostingsFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this).get(PostingsViewModel::class.java) }
    private lateinit var viewDataBinding: FragmentPostingsBinding
    private lateinit var activity: Activity
    var pos = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentPostingsBinding.inflate(layoutInflater, container, false)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewmodel = viewModel

        with(viewDataBinding.fragPostingsVp2) {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            layoutDirection = ViewPager2.LAYOUT_DIRECTION_RTL
            offscreenPageLimit = 2

            adapter = PostingsRvAdapter()
            setPageTransformer(ZoomOutSidePageTransformer())
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    viewModel.updateDate(position)
                    super.onPageSelected(position)
                }
            })
        }

        val dummyList: ArrayList<Posting> = arrayListOf()
        activity
        dummyList.add(
            Posting(
                JodaTimeHelper.getCurrentTimeStamp(),
                JodaTimeHelper.convertTimeStamp("2021/01/22 12:00:00"),
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.k7
                )!!, arrayListOf("tag1", "tag2", "tag3")
            )
        )
        dummyList.add(
            Posting(
                JodaTimeHelper.getCurrentTimeStamp(),
                JodaTimeHelper.convertTimeStamp("2021/01/23 12:00:00"),
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.k4
                )!!, arrayListOf("tag1", "tag2", "tag3")
            )
        )
        dummyList.add(
            Posting(
                JodaTimeHelper.getCurrentTimeStamp(),
                JodaTimeHelper.convertTimeStamp("2021/01/24 12:00:00"),
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.k3
                )!!, arrayListOf("tag1", "tag2", "tag3")
            )
        )
        dummyList.add(
            Posting(
                JodaTimeHelper.getCurrentTimeStamp(),
                JodaTimeHelper.convertTimeStamp("2021/01/25 12:00:00"),
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.k4
                )!!, arrayListOf("tag1", "tag2", "tag3")
            )
        )
        dummyList.add(
            Posting(
                JodaTimeHelper.getCurrentTimeStamp(),
                JodaTimeHelper.convertTimeStamp("2021/01/26 12:00:00"),
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.k3
                )!!, arrayListOf("tag1", "tag2", "tag3")
            )
        )
        dummyList.add(
            Posting(
                JodaTimeHelper.getCurrentTimeStamp(),
                JodaTimeHelper.convertTimeStamp("2021/01/27 12:00:00"),
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.k4
                )!!, arrayListOf("tag1", "tag2", "tag3")
            )
        )
        dummyList.add(
            Posting(
                JodaTimeHelper.getCurrentTimeStamp(),
                JodaTimeHelper.convertTimeStamp("2021/01/28 12:00:00"),
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.k7
                )!!, arrayListOf("tag1", "tag2", "tag3")
            )
        )
        dummyList.add(
            Posting(
                JodaTimeHelper.getCurrentTimeStamp(),
                JodaTimeHelper.convertTimeStamp("2021/01/29 12:00:00"),
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.k8
                )!!, arrayListOf("tag1", "tag2", "tag3")
            )
        )
        dummyList.add(
            Posting(
                JodaTimeHelper.getCurrentTimeStamp(),
                JodaTimeHelper.convertTimeStamp("2021/01/30 12:00:00"),
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.k9
                )!!, arrayListOf("tag1", "tag2", "tag3")
            )
        )
        dummyList.add(
            Posting(
                JodaTimeHelper.getCurrentTimeStamp(),
                JodaTimeHelper.convertTimeStamp("2021/01/31 12:00:00"),
                ContextCompat.getDrawable(
                    activity,
                    R.drawable.k10
                )!!, arrayListOf("tag1", "tag2", "tag3")
            )
        )

        viewModel.start(dummyList)
    }
}