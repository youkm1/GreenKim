package com.example.newgreenkim

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.card.MaterialCardView
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.view.CalendarView
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthScrollListener
import com.kizitonwose.calendar.view.ViewContainer
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random

class SettingActivity : AppCompatActivity() {
    companion object {
        const val PICK_IMAGE_REQUEST = 1
    }

    private val uploadedImages = mutableListOf<Uri>()

    private lateinit var imageView7: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting);

        initUi()
    }

    private fun initUi() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imageView7 = findViewById(R.id.imageView7)
        imageView7.setOnClickListener {
            showImageSourceDialog()
        }

        val textView1: TextView = findViewById(R.id.textView1)
        val switchView: SwitchCompat = findViewById(R.id.switchView1)

        switchView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textView1.text = "댓글 알림"
            } else {
                textView1.text = "댓글 알림"
            }
        }

        val testButton: Button = findViewById<Button>(R.id.button2)
        testButton.setOnClickListener {
            val nextIntent = Intent(this, MyPageActivity1::class.java)
            startActivity(nextIntent)
        }

        val testButton1: Button = findViewById<Button>(R.id.button3)
        testButton1.setOnClickListener {
            val nextIntent = Intent(this, MyPageActivity2::class.java)
            startActivity(nextIntent)
        }

        val testButton4: Button = findViewById<Button>(R.id.button7)
        testButton4.setOnClickListener {
            val nextIntent = Intent(this, WithdrawActivity::class.java)
            startActivity(nextIntent)
        }

        //region Initialize calendar view
        val calendarView = findViewById<CalendarView>(R.id.calendar_view)
        val currentMonthTextView = findViewById<TextView>(R.id.current_month_text_view)
        val previousMonthButton = findViewById<ImageView>(R.id.previous_month_button)
        val nextMonthButton = findViewById<ImageView>(R.id.next_month_button)

        // 이번달
        val currentMonth = YearMonth.now()

        // 시작월 (시작월 전으로는 달력 표시 안함)
        val startMonth = currentMonth.minusMonths(10)

        // 종료월 (종료월 이후로는 달력 표시 안함)
        val endMonth = currentMonth.plusMonths(10)

        // Week 의 시작 요일 (한국은 일요일부터 시작)
        val firstDayOfWeek = WeekFields.of(Locale.KOREA).firstDayOfWeek

        calendarView.monthScrollListener = object : MonthScrollListener {
            override fun invoke(month: CalendarMonth) {
                previousMonthButton.run {
                    isEnabled = startMonth != month.yearMonth
                    setOnClickListener {
                        calendarView.smoothScrollToMonth(month.yearMonth.minusMonths(1))
                    }
                }

                nextMonthButton.run {
                    isEnabled = endMonth != month.yearMonth
                    setOnClickListener {
                        calendarView.smoothScrollToMonth(month.yearMonth.plusMonths(1))
                    }
                }

                currentMonthTextView.text = SimpleDateFormat("MMM", Locale.US).format(
                    Calendar.getInstance()
                        .apply {
                            set(month.yearMonth.year, month.yearMonth.monthValue - 1, 1)
                        }
                        .time
                )
            }
        }

        calendarView.run {
            dayBinder = object : MonthDayBinder<DayViewContainer> {
                // 작성한 글이 없을때의 색상
                private val noItemColor = Color.parseColor("#f0f0f0")

                // 작성한 글이 있을때의 색상 (글 갯수에 따라 해당 색상의 Alpha (투명도) 값을 달리 한다.)
                private val hasItemColor = Color.parseColor("#ff868c")

                override fun bind(container: DayViewContainer, data: CalendarDay) {
                    val view = container.cardView

                    // 글 갯수 (랜덤)
                    val count = Random.nextInt(0, 5)

                    if (count == 0) {
                        view.run {
                            setCardBackgroundColor(noItemColor)
                            alpha = 1f
                        }
                    } else {
                        // Alpha 값
                        // 랜덤으로 가져오는 글 갯수는 0 ~ 4 까지 이다.
                        // 0을 제외한 나머지 1 ~ 4 값을 4 (0을 제외한 최대 갯수) 로 나눈 값을 알파값이다고 한다.
                        val alpha = (0xff * (count / 4f)) / 0xff

                        view.run {
                            setCardBackgroundColor(hasItemColor)
                            this.alpha = alpha
                        }
                    }
                }

                override fun create(view: View): DayViewContainer = DayViewContainer(view)
            }

            setup(startMonth, endMonth, firstDayOfWeek)
            scrollToMonth(currentMonth)
        }
        //endregion
    }

    private fun showImageSourceDialog() {
        val options = arrayOf("갤러리에서 사진 가져오기")

        AlertDialog.Builder(this)
            .setTitle("이미지 업로드")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> openGallery()
                }
            }
            .show()
    }

    private fun openGallery() {
        val galleryIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    val selectedImageUri: Uri? = data?.data
                    selectedImageUri?.let {
                        uploadedImages.add(it)
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private class DayViewContainer(view: View) : ViewContainer(view) {
        val cardView = view.findViewById<MaterialCardView>(R.id.card_view)
    }
}