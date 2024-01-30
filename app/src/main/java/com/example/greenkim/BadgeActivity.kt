package com.example.greenkim

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class BadgeActivity : AppCompatActivity() {
    //획득한 뱃지에 Blank 넣어두기
    private val earnedBadgeList: List<BadgeList> = listOf(BadgeList.ADVENTURER)
    private val unearnedBadgeList: List<BadgeList> = BadgeList.values().filter { !it.isEarned }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)

        // 뒤로가기
        val backButton: ImageButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }

        // Earned Badge 설정
        val earnedBadgeRecyclerView: RecyclerView = findViewById(R.id.earned_badge_recycler_view)
        earnedBadgeRecyclerView.layoutManager = GridLayoutManager(this, 5)
        val earnedAdapter = BadgeAdapter(earnedBadgeList) { clickedBadge ->
            showBadgePopup(clickedBadge)
        }
        earnedBadgeRecyclerView.adapter = earnedAdapter

        // Unearned Badge 설정
        val unearnedBadgeRecyclerView: RecyclerView = findViewById(R.id.unearned_badge_recycler_view)
        unearnedBadgeRecyclerView.layoutManager = GridLayoutManager(this, 5)
        val unearnedAdapter = BadgeAdapter(unearnedBadgeList) { clickedBadge ->
            showBadgePopup(clickedBadge)
        }
        unearnedBadgeRecyclerView.adapter = unearnedAdapter

    }

    // 뱃지 정보를 받아 팝업창에 표시하는 함수
    private fun showBadgePopup(badgeData: BadgeList) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.badge_popup_layout)

        val badgeImage = dialog.findViewById<ImageView>(R.id.popup_badge_image)
        val badgeText = dialog.findViewById<TextView>(R.id.popup_badge_text)
        val badgeAchievementText = dialog.findViewById<TextView>(R.id.badge_achievement)

        badgeImage.setImageResource(badgeData.badgeImg)
        badgeText.text = badgeData.badge
        badgeAchievementText.text=badgeData.badgeAchieve

        val closeButton = dialog.findViewById<ImageView>(R.id.popup_close_button)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
