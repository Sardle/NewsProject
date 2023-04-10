package com.example.newsproject.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ViewModelFactory
import com.example.feature.di.FeatureDepsStore
import com.example.feature.ui.SearchFragment
import com.example.newsproject.R
import com.example.newsproject.databinding.ActivityMainBinding
import com.example.newsproject.di.app.MyApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: NewsViewModel by viewModels { factory }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        FeatureDepsStore.deps = (applicationContext as MyApplication).appComponent
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupSearchPage()
        setupNewsRecyclerView()
        observeTechnicLiveData()
    }

    private fun setupSearchPage() {
        binding.search.setOnClickListener {
            binding.recycler.isVisible = false
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame, SearchFragment())
                .commit()
        }
    }

    private fun setupNewsRecyclerView() {
        val newsAdapter = NewsAdapter()

        binding.recycler.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeTechnicLiveData() {
        viewModel.newsLiveData.observe(this) { technicList ->
            binding.recycler.adapter?.let { adapter ->
                if (adapter is NewsAdapter) {
                    adapter.setItems(technicList)
                }
            }
        }
    }
}