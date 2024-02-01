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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_navi, container, false)

        val homeButton: ImageButton = view.findViewById(R.id.button_home)
        val languageButton: ImageButton = view.findViewById(R.id.button_language)
        val accountButton: ImageButton = view.findViewById(R.id.button_account)

        // 현재 액티비티를 확인
        val currentActivity = activity

//        // MainActivity에 있을 때 button_home의 색상을 변경
//        if (currentActivity is MainActivity) {
//            homeButton.setColorFilter(Color.parseColor("#288156"), android.graphics.PorterDuff.Mode.SRC_IN)
//        }

        // CommunityActivity에 있을 때 button_language의 색상을 변경
        if (currentActivity is CommunityActivity) {
            languageButton.setColorFilter(Color.parseColor("#288156"), android.graphics.PorterDuff.Mode.SRC_IN)
        }

        homeButton.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            activity?.overridePendingTransition(0, 0)
        }

        languageButton.setOnClickListener {
            val intent = Intent(activity, CommunityActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            activity?.overridePendingTransition(0, 0)
        }

        // 마지막 이미지는 클릭 이벤트를 냅둠

        return view
    }
}
