package com.example.newsproject.ui

import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.NewsData
import com.example.feature.databinding.RvNewsBinding

class NewsViewHolder(private val binding: RvNewsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: NewsData) {
        binding.title.text = item.title
        binding.author.text = item.author
        getPoster(item.urlToImage, binding.image)

        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.url)
            binding.title.context.startActivity(intent)
        }
    }

    private fun getPoster(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }
}