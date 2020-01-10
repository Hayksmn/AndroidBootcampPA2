package com.self.admin.bootcamp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.stetho.Stetho


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)
    }
}
