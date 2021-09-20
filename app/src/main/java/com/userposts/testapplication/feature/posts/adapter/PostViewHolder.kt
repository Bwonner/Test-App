package com.userposts.testapplication.feature.posts.adapter

import androidx.recyclerview.widget.RecyclerView
import com.userposts.testapplication.databinding.ItemPostBinding
import com.userposts.testapplication.domain.model.UserPostResult

class PostViewHolder(private val itemPostBinding: ItemPostBinding) :
    RecyclerView.ViewHolder(itemPostBinding.root) {

    fun bind(item: UserPostResult) {
        itemPostBinding.itemPostTitle.text = item.title
        itemPostBinding.itemPostUserName.text = item.name
    }
}