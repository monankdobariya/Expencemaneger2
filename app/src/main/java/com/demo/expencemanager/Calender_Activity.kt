package com.demo.expencemanager

import android.os.Bundle
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemanagerproject.databinding.ActivityCalenderBinding


class Calender_Activity : AppCompatActivity() {
    lateinit var binding: ActivityCalenderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calender()
    }

    private fun calender() {

//        binding.calenderview.date
//        binding.calenderview.firstDayOfWeek
//        binding.calenderview.maxDate
//        binding.calenderview.minDate
//        binding.calenderview.showWeekNumber
        binding.calenderview.setOnDateChangeListener(CalendarView.OnDateChangeListener { calendarView, year, Month, dayOfMonth ->

            val Date = (dayOfMonth.toString() + "-" + (Month + 1) + "-" + year)
            binding.TxtDate.setText(Date)
        })

    }
}