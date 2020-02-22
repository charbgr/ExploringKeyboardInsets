package com.github.charbgr.exploringkeyboard

import android.os.Bundle
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mylayout.post { setUp() }
        keyboardInfo.setOnClickListener {
            dumpKeyboard()
        }
    }

    private fun setUp() {
        mylayout.rootView.setOnApplyWindowInsetsListener { view, windowInsets ->
            println("IS KEYBOARD VISIBLE: ${windowInsets.isVisible(WindowInsets.Type.ime())}")
            windowInsets
        }
    }

    private fun dumpKeyboard() {
        val windowInsets = mylayout.rootWindowInsets
        val imeInset = windowInsets.getInsets(WindowInsets.Type.ime())
        val dump = """
            L: ${imeInset.left}
            T: ${imeInset.top}
            R: ${imeInset.right}
            B: ${imeInset.bottom}
        """.trimIndent()

        keyboardInfo.text = dump
    }
}
