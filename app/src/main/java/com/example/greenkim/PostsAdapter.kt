package com.example.greenkim

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.greenkim.R
import com.example.greenkim.databinding.RecyclerviewItemBinding

// 게시물 데이터 클래스입니다. 사용자 정의 데이터 클래스인 'posts'를 사용합니다.
import com.example.greenkim.posts

// PostsAdapter 클래스는 RecyclerView.Adapter를 상속하고 있습니다.
class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    // 게시물 목록을 담는 리스트입니다.
    var listData = mutableListOf<posts>()

    // 게시물 클릭 이벤트를 처리하기 위한 리스너입니다.
    var onPostItemClickListener: OnPostItemClickListener? = null

    // 뷰홀더 클래스를 생성하는 메서드입니다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        // XML 레이아웃 파일을 사용하여 뷰홀더 객체를 생성합니다.
        val binding = RecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PostViewHolder(binding)
    }

    // 목록의 아이템 개수를 반환하는 메서드입니다.
    override fun getItemCount(): Int {
        return listData.size
    }

    // 뷰홀더에 데이터를 바인딩하는 메서드입니다.
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = listData[position]
        holder.bind(post)
    }

    // 뷰홀더 클래스는 RecyclerView.ViewHolder를 상속합니다.
    inner class PostViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // 뷰홀더가 생성될 때 초기화 작업을 수행합니다.
        init {
            // 게시물을 클릭했을 때 동작하는 리스너입니다.
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedPost = listData[position]
                    // 게시물 클릭 이벤트를 리스너를 통해 전달합니다.
                    onPostItemClickListener?.onPostItemClick(clickedPost)
                }
            }
        }

        // 데이터를 뷰에 바인딩하는 메서드입니다.
        fun bind(post: posts) {
            binding.nickName.text = post.nickname
            binding.chatCounts.text = post.chatCounts
            // 필요한 경우 다른 데이터를 설정합니다.
        }
    }

    // 게시물 클릭 이벤트를 처리하는 리스너 인터페이스입니다.
    interface OnPostItemClickListener {
        fun onPostItemClick(post: posts)
    }
}
