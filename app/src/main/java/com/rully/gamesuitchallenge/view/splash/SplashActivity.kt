package com.rully.gamesuitchallenge.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rully.gamesuitchallenge.databinding.ActivitySplashBinding
import com.rully.gamesuitchallenge.view.landingpage.LandingActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val imageUrl = "https://i.ibb.co/HC5ZPgD/splash-screen1.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            Glide.with(this@SplashActivity)
                .load(imageUrl)
                .into(ivSplash1)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LandingActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}