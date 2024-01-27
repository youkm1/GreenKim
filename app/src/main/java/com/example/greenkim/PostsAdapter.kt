package com.example.greenkim

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.greenkim.databinding.RecyclerviewItemBinding

class PostsAdapter : RecyclerView.Adapter<Holder>() {

    var listData = mutableListOf<posts>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val post = listData[position]
        holder.setPost(post)
    }
}

class Holder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setPost(post: posts) {
        binding.nickName.text = "아기자기"
        binding.chatCounts.text = "${post.no}"

        // 좋아요 버튼 클릭 이벤트 처리
        binding.Like.setOnClickListener {
            // 좋아요 상태 변경
            post.liked = !post.liked
            // 좋아요 상태에 따라 이미지 변경
            updateLikeImage(post.liked)
        }

        // 초기 좋아요 이미지 설정
        updateLikeImage(post.liked)
    }

    private fun updateLikeImage(liked: Boolean) {
        // 좋아요 상태에 따라 이미지 변경
        if (liked) {
            // 선택된 경우
            binding.Like.setImageResource(R.drawable.baseline_favorite_24)
            // 선택된 경우의 색상 설정
        } else {
            // 선택되지 않은 경우
            binding.Like.setImageResource(R.drawable.baseline_favorite_border_24)
            // 원래의 색상 설정
        }
    }
}