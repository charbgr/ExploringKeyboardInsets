package com.github.charbgr.exploringkeyboard

import android.os.Bundle
import android.os.CancellationSignal
import android.view.WindowInsets
import android.view.WindowInsetsAnimationControlListener
import android.view.WindowInsetsAnimationController
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val insetsType = WindowInsets.Type.ime()
    private val animationDurationInMillis = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: find when is ready!
        mylayout.postDelayed(
            { setUp(); Toast.makeText(this, "TEST", Toast.LENGTH_SHORT).show() },
            2000
        )
        keyboardInfo.setOnClickListener {
            window.insetsController?.show(insetsType)
            it.postDelayed(
                { Toast.makeText(this, "Keyboard is open!", Toast.LENGTH_SHORT).show() },
                animationDurationInMillis
            )
        }
    }

    private fun setUp() {
        val cancellationSignal = window.insetsController?.controlWindowInsetsAnimation(
            insetsType, // type
            animationDurationInMillis, // durationInMillis
            BounceInterpolator(), // interpolator
            object : WindowInsetsAnimationControlListener { // listener
                override fun onCancelled() {
                    println("WindowInsetsAnimationControlListener : CANCELED")
                }

                override fun onReady(p0: WindowInsetsAnimationController, p1: Int) {
                    println("WindowInsetsAnimationControlListener : READY")
                    println("Insets Type: $p1 (isIme ${p1 == WindowInsets.Type.ime()})")
                }
            })

        cancellationSignal?.setOnCancelListener {
            println("CancellationSignal : onCancel")
        }
    }
}
