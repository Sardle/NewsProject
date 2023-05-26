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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ViewModelFactory
import com.example.feature.databinding.FragmentSearchBinding
import com.example.feature.di.DaggerFeatureComponent
import com.example.feature.di.FeatureDepsProvider
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

        setupSearchRecyclerView()
        observeTechnicLiveData()
    }

    private fun search() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    viewModel.search(s.toString())
                }
            }
        }

        binding.search.addTextChangedListener(textWatcher)
    }

    private fun setupSearchRecyclerView() {
        val newsAdapter = SearchAdapter()

        binding.recyclerSearch.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        search()
    }

    private fun observeTechnicLiveData() {
        viewModel.newsLiveData.observe(viewLifecycleOwner) { technicList ->
            binding.recyclerSearch.adapter?.let { adapter ->
                if (adapter is SearchAdapter) {
                    adapter.setItems(technicList)
                }
            }
        }
    }
}