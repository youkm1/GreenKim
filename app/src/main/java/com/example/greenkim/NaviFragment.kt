package com.example.greenkim

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class NaviFragment : Fragment() {

    private lateinit var languageButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃 초기화
        val view = inflater.inflate(R.layout.fragment_navi, container, false)

        // 홈, 언어, 계정 버튼 가져오기
        val homeButton: ImageButton = view.findViewById(R.id.button_home)
        val languageButton: ImageButton = view.findViewById(R.id.button_language)

        // 현재 액티비티 가져오기
        val currentActivity = activity

        // 만약 현재 액티비티가 CommunityActivity이면 언어 버튼 색상 업데이트
        if (currentActivity is CommunityActivity) {
            currentActivity.updateLanguageButtonColor(languageButton)
        }

        // 홈 버튼 클릭 이벤트 처리
        homeButton.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            activity?.overridePendingTransition(0, 0)
        }

        // 언어 버튼 클릭 이벤트 처리
        languageButton.setOnClickListener {
            val intent = Intent(activity, CommunityActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            activity?.overridePendingTransition(0, 0)
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        if (::languageButton.isInitialized) {
            val currentActivity = activity
            if (currentActivity is CommunityActivity) {
                currentActivity.updateLanguageButtonColor(languageButton)
            }
        }
    }
}