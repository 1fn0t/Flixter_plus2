package com.example.flixter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flixster.ShowFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.showContent, ShowFragment(), null).commit()
    }

}