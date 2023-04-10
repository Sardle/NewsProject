package com.example.feature.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.NewsData
import com.example.domain.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val trigger = MutableStateFlow("")

    fun setQuery(query: String) {
        trigger.value = query
    }

    val results: Flow<List<NewsData>> = trigger.mapLatest { query ->
        if (query.isNotEmpty()) {
            delay(500)
            repository.search(query)
        } else repository.search(NOTHING)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    companion object {
        private const val NOTHING = "INVALID:NOTHING"
    }
}