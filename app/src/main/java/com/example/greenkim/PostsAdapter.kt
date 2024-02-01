import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.greenkim.R
import com.example.greenkim.databinding.RecyclerviewItemBinding
<<<<<<< HEAD

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    var listData = mutableListOf<posts>()
    var onPostItemClickListener: OnPostItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
=======
import com.example.greenkim.posts

class PostsAdapter : RecyclerView.Adapter<Holder>() {

    var listData = mutableListOf<posts>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
>>>>>>> main
        val binding = RecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

<<<<<<< HEAD
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
=======
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
            // 좋아요 애니메이션 추가
            animateLike(post.liked)
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

    private fun animateLike(liked: Boolean) {
        // 좋아요 애니메이션 추가
        val animator = if (liked) {
            ObjectAnimator.ofFloat(binding.Like, "scaleX", 1f, 1.2f, 1f)
        } else {
            ObjectAnimator.ofFloat(binding.Like, "scaleX", 1f, 0.8f, 1f)
        }

        animator.duration = 300
        animator.interpolator = AccelerateInterpolator()
        animator.start()
    }
}
>>>>>>> main
