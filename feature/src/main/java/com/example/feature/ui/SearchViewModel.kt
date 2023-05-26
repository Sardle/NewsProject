package com.example.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.NewsData
import com.example.domain.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _newsLiveData = MutableLiveData<List<NewsData>>()
    val newsLiveData: LiveData<List<NewsData>> get() = _newsLiveData

    private val composite = CompositeDisposable()

    private val publishSubject by lazy {
        PublishSubject.create<String>()
    }

    init {
        observeSearch()
    }

    fun search(query: String) {
        publishSubject.onNext(query)
    }

    private fun observeSearch() {
        val disposable = publishSubject.debounce(500, TimeUnit.MILLISECONDS)
            .switchMapSingle {
                repository.search(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _newsLiveData.value = it }
        composite.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}