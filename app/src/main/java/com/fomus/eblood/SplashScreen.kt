package com.fomus.eblood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            naviagte()
        }, 1000)
        logo.setOnClickListener { naviagte() }
    }

    private fun naviagte() {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}