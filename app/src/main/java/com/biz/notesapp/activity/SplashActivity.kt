package com.biz.notesapp.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.biz.notesapp.MainActivity
import com.biz.notesapp.R
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { true }
        }

        Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
        },1000)

    }
}