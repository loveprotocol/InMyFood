package com.yes.inmyfood

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * TODO
 * 게시글 리스트 초기 load (10개씩 로드, 최대 30개 보여주기)
 * 게시글 리스트 아이템 추가 load
 *
 */
class PostingListViewModel : ViewModel() {

    private val _postings = MutableLiveData<List<Posting>>()
    val postings: LiveData<List<Posting>>
        get() = _postings

    private var init = false
    private val _dummyList:ArrayList<Posting> = arrayListOf()

    companion object {
        const val MAX_ITEM_COUNT = 30
    }
    fun start(dummyList: List<Posting>) {
        if (!init) {
            _dummyList.addAll(dummyList)
        }

        loadPostings()
    }

    fun loadPostings() {

        _postings.value = _dummyList.subList(0, 9)
    }
}