package com.example.newsproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.NewsData
import com.example.feature.databinding.RvNewsBinding

class NewsAdapter() : RecyclerView.Adapter<NewsViewHolder>() {

    private val listNewsData = mutableListOf<NewsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemRvNewsBinding = RvNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(itemRvNewsBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(listNewsData[position])
    }

    override fun getItemCount(): Int = listNewsData.size

    fun setItems(items: List<NewsData>) {
        listNewsData.clear()
        listNewsData.addAll(items)
        notifyDataSetChanged()
    }
}