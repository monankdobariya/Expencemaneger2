package com.demo.expencemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expensemanagerproject.R
import com.example.expensemanagerproject.databinding.ActivityReportBinding

class ReportActivity : AppCompatActivity() {
    lateinit var binding: ActivityReportBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgback.setOnClickListener {
            onBackPressed()
        }
    }
}