package com.example.flixter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.flixster.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val imageView = findViewById<ImageView>(R.id.showPoster)
        val nameView = findViewById<TextView>(R.id.showName)
        val averageView = findViewById<TextView>(R.id.showAvg2)


        val show = intent.getSerializableExtra(SHOW_EXTRA) as Show

        nameView.text = show.showName
        averageView.text = show.showAverage

        Glide.with(this)
            .load(BASE_URL + show.showPoster)
            .centerInside()
            .into(imageView)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.castContent, CastFragment(show.showId), null).commit()
    }
//    https://api.themoviedb.org/3/tv/{tv_id}/credits?api_key=<<api_key>>&language=en-US
}