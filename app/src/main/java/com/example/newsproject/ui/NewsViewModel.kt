package com.example.newsproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.NewsData
import com.example.domain.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _newsLiveData = MutableLiveData<List<NewsData>>()
    val newsLiveData: LiveData<List<NewsData>> get() = _newsLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    init {
        getNews()
        getAll()
        setUserToken()
    }

    private fun getNews() {
        viewModelScope.launch() {
            repository.getNews()
        }
    }

    private fun getAll() {
        _loadingLiveData.value = true
        viewModelScope.launch {
            repository.getNewsFromDataBase().collect {
                _newsLiveData.value = it
                _loadingLiveData.value = false
            }
        }
    }

    private fun setUserToken() {
        repository.setUserToken(USER_TOKEN)
    }

    companion object {
        private const val USER_TOKEN = "6e2215d6c8824752abea6defbc421007"
    }
}