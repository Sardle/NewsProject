package com.example.feature.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ViewModelFactory
import com.example.feature.databinding.FragmentSearchBinding
import com.example.feature.di.DaggerFeatureComponent
import com.example.feature.di.FeatureDepsProvider
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: SearchViewModel by viewModels { factory }
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerFeatureComponent.builder()
            .deps(FeatureDepsProvider.deps)
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNewsRecyclerView()
        setupSearchViewModel()
        setQueryToSearch()
    }

    private fun setQueryToSearch() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (start != 0) {
                    viewModel.setQuery(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.search.addTextChangedListener(textWatcher)
    }

    private fun setupNewsRecyclerView() {
        val newsAdapter = SearchAdapter()

        binding.recyclerSearch.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupSearchViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.results
                .flowWithLifecycle(
                    viewLifecycleOwner.lifecycle,
                    Lifecycle.State.STARTED
                )
                .distinctUntilChanged()
                .collect { data ->
                    binding.recyclerSearch.adapter?.let { adapter ->
                        if (adapter is SearchAdapter) {
                            adapter.setItems(data)
                        }
                    }
                }
        }
    }
}