package com.example.greenkim

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.example.newgreenkim.databinding.ActivityCalendarBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarActivity : AppCompatActivity() {
    lateinit var binding: ActivityCalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val today = CalendarDay.today()
        val disabledDates = hashSetOf<CalendarDay>()
        disabledDates.add(CalendarDay.from(2024, 2, 1))

        binding.calendarView.apply {
            // 휴무일 지정을 위한 Decorator 설정
            addDecorator(TodayDecorator())
            // 요일을 위해 {"월", "화", ..., "일"} 배열을 추가한다.
            setWeekDayLabels(arrayOf("월", "화", "수", "목", "금", "토", "일"))
            // 달력 상단에 `월 년` 포맷을 수정하기 위해 TitleFormatter 설정
            setTitleFormatter(MyTitleFormatter())
        }

        DateFormatTitleFormatter()
    }

    inner class MyTitleFormatter : TitleFormatter {
        override fun format(day: CalendarDay?): CharSequence {
            val simpleDateFormat =
                SimpleDateFormat("yyyy.MM", Locale.US) //"February 2016" format

            return simpleDateFormat.format(Calendar.getInstance().getTime())
        }

    }

    inner class DayDisableDecorator : DayViewDecorator {
        private var dates = HashSet<CalendarDay>()
        private var today: CalendarDay

        constructor(dates: HashSet<CalendarDay>, today: CalendarDay) {
            this.dates = dates
            this.today = today
        }

        override fun shouldDecorate(day: CalendarDay): Boolean {
            return dates.contains(day) || day.isBefore(today)
        }

        override fun decorate(view: DayViewFacade?) {
            view?.let { it.setDaysDisabled(true) }
        }
    }

    class TodayDecorator : DayViewDecorator {
        private var date = CalendarDay.today()

        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return day?.equals(date)!!
        }

        override fun decorate(view: DayViewFacade?) {
            view?.addSpan(StyleSpan(Typeface.BOLD))
            view?.addSpan(RelativeSizeSpan(1.4f))
            view?.addSpan(ForegroundColorSpan(Color.parseColor("#1D872A")))
        }

    }
}

