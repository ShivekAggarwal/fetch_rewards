package com.example.fetch_task.ui.splash

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.fetch_task.R
import com.example.fetch_task.ui.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val imageViewLogo = findViewById<ImageView?>(R.id.imageViewLogo)
        val moveUp = AnimationUtils.loadAnimation(this, R.anim.move_up)
        imageViewLogo.startAnimation(moveUp)

        moveUp.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity ::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
