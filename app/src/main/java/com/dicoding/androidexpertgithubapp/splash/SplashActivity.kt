package com.dicoding.androidexpertgithubapp.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dicoding.androidexpertgithubapp.databinding.ActivitySplashBinding
import com.dicoding.androidexpertgithubapp.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    //Splash screen icon source: https://pic.onlinewebfonts.com/svg/img_129492.png
    private lateinit var binding: ActivitySplashBinding
    private val splashTime: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, splashTime)
    }
}