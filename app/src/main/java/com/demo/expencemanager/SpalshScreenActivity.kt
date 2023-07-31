package com.demo.expencemanager

import android.content.Intent
import android.content.pm.PackageInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.expensemanagerproject.databinding.ActivitySplashscreenBinding

class SpalshScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
        //version
        val manager = packageManager
        val info: PackageInfo = manager.getPackageInfo(
            packageName, 0
        )
        val version: String = info.versionName
        binding.txtversion.text = version
    }
}
