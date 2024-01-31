package com.example.greenkim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

enum class BadgeList (
    val badge:String,
    val badgeNum:Int,
    val badgeAchieve:String,
    val badgeImg:Int,
    var isProfileBadge: Boolean = false,
    var isEarned: Boolean = false
){
    BLANK("",-1, "", R.drawable.ic_launcher_foreground),
    MENTOR("그린 멘토",5,"질문 카테고리 댓글 5회 작성하기",  R.drawable.mentor),
    MENTEE("그린 멘티",5,"질문 카테고리 글 5회 작성하기", R.drawable.mentee),
    EARLYBIRD("얼리버드",3,"오전 9시 이전에 인증글 3회 작성하기", R.drawable.earlybird),
    NORANG("김노랑",10,"인증글 10회 작성하기", R.drawable.norang),
    YEONDU("김연두",20,"인증글 20회 작성하기", R.drawable.yeondu),
    GREEN("김그린",50,"인증글 50회 작성하기", R.drawable.green),
    RECEIPT_3("영수증도 전자로",3,"전자영수증 발급 인증글 3회 작성하기", R.drawable.electronic_receipt),
    RECEIPT_10("디지털 환경 지킴이",10,"전자영수증 발급 인증글 10회 작성하기", R.drawable.digital_protector),
    REUSABLE_3("re: user",3,"리유저블 활동 인증글 3회 작성하기", R.drawable.re_user),
    REUSABLE_10("re: 마스터",10,"리유저블 활동 인증글 10회 작성하기", R.drawable.re_master),
    PLASTIC_3("자연과 친구하기",3,"플라스틱 프리 인증글 3회 작성하기", R.drawable.eco_friend),
    PLASTIC_10("자연과 물아일체",10,"플라스틱 프리 인증글 10회 작성하기", R.drawable.eco_one),
    PLOGGING_3("자연 속에 만 보 추구",3,"플로깅 인증글 3회 작성하기", R.drawable.jamanchu),
    PLOGGING_10("클린로드의 개척자",10,"플로깅 인증글 10회 작성하기", R.drawable.clean_road),
    REFORM_3("손재주 아티스트",3,"리폼 인증글 3회 작성하기", R.drawable.artist),
    REFORM_10("친환경 대가",10,"리폼 인증글 10회 작성하기", R.drawable.eco_master),
    TRANSPORT_3("탄소 헤이터",3,"대중교통, 자전거 인증글 3회 작성하기", R.drawable.c_hater),
    TRANSPORT_10("지구 세이버",10,"대중교통, 자전거 인증글 10회 작성하기", R.drawable.e_saver),
    ETC_3("아마추어 기타리스트",3,"기타 인증글 3회 작성하기", R.drawable.amateur_guitarist),
    ETC_10("프로 기타리스트",10,"기타 인증글 10회 작성하기", R.drawable.pro_guitarist),
    ADVENTURER("환경 모험가",-1,"각 카테고리별 인증글 1회 작성하기",R.drawable.adventurer),
    DOCTOR("새싹 김그린",-1,"튜토리얼 열람하기",R.drawable.sprout),
    GOLDEN_KIMGREEN("황금 김그린",-1,"모든 뱃지 획득하표",R.drawable.golden_kimgreen)
}

// RecyclerView 어댑터
class BadgeAdapter(
    var badgeList: List<BadgeList>,
    private val earnedBadgeClickListener: (BadgeList) -> Unit,
    private val unearnedBadgeClickListener: (BadgeList) -> Unit
) : RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder>() {

    // BLANK 항목을 제외한 리스트 생성
    private var filteredBadgeList = badgeList.filterNot { it == BadgeList.BLANK }

    inner class BadgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val badgeImg: ImageView = itemView.findViewById(R.id.badge_image)
        val badge: TextView = itemView.findViewById(R.id.badge_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.badge_layout, parent, false)
        return BadgeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        // filteredBadgeList를 기준으로 onBindViewHolder 처리
        val currentItem = filteredBadgeList[position]

        holder.badge.text = currentItem.badge
        holder.badgeImg.setImageResource(currentItem.badgeImg)

        // 뱃지 클릭 시 해당 뱃지 정보 전달
        holder.itemView.setOnClickListener {
            if (currentItem.isEarned) {
                // 획득한 뱃지인 경우 대표 뱃지 변경 다이얼로그 표시
                earnedBadgeClickListener.invoke(currentItem)
            } else {
                // 획득하지 않은 뱃지인 경우 뱃지 팝업 표시
                unearnedBadgeClickListener.invoke(currentItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return filteredBadgeList.size
    }

    fun updateBadgeList(newBadgeList: List<BadgeList>) {
        badgeList = newBadgeList
        filteredBadgeList = badgeList.filterNot { it == BadgeList.BLANK }
        notifyDataSetChanged()
    }
}
