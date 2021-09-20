package com.userposts.testapplication.feature.posts.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.userposts.testapplication.databinding.ItemPostBinding
import com.userposts.testapplication.domain.model.UserPostResult

class PostsAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private val items = mutableListOf<UserPostResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<UserPostResult>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}