package com.example.newsproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.NewsData
import com.example.domain.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _newsLiveData = MutableLiveData<List<NewsData>>()
    val newsLiveData: LiveData<List<NewsData>> get() = _newsLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val composite = CompositeDisposable()

    init {
        getNews()
        getAll()
        setUserToken()
    }

    private fun getNews() {
        val disposable = repository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        composite.add(disposable)
    }

    private fun getAll() {
        _loadingLiveData.value = true
        val disposable = repository.getNewsFromDataBase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { value ->
                _loadingLiveData.value = false
                _newsLiveData.value = value
            }
        composite.add(disposable)
    }

    private fun setUserToken() {
        repository.setUserToken(USER_TOKEN)
    }

    override fun onCleared() {
        super.onCleared()

        composite.clear()
    }

    companion object {
        private const val USER_TOKEN = "6e2215d6c8824752abea6defbc421007"
    }
}