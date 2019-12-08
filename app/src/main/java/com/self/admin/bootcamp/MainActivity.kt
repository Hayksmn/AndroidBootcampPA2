package com.self.admin.bootcamp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customIncrement.setOnClickListener {
                progressBarCustom.increment(0.1f)
        }

        customDecrement.setOnClickListener {
            progressBarCustom.decrement(0.1f)
        }
    }

}
