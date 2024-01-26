package com.example.greenkim

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greenkim.databinding.RecyclerviewItemBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    var listData = mutableListOf<posts>()
    var onPostItemClickListener: OnPostItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = RecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = listData[position]
        holder.bind(post)
    }

    inner class PostViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedPost = listData[position]
                    onPostItemClickListener?.onPostItemClick(clickedPost)
                }
            }
        }

        fun bind(post: posts) {
            binding.nickName.text = post.nickname
            binding.chatCounts.text = post.chatCounts
            // Set other data as needed
        }
    }

    // Interface for item click listener
    interface OnPostItemClickListener {
        fun onPostItemClick(post: posts)
    }
}
