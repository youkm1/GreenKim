package com.example.greenkim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.newgreenkim.R

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
    }

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

        override fun getItemCount(): Int {
            return 5 // 페이지 개수
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> TutorialFragment1()
                1 -> TutorialFragment2()
                2 -> TutorialFragment3()
                3 -> TutorialFragment4()
                4 -> TutorialFragment5()
                else -> TutorialFragment1()
            }
        }
    }
}