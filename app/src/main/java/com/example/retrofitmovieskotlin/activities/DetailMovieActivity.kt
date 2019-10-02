package com.example.retrofitmovieskotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitmovieskotlin.R
import com.example.retrofitmovieskotlin.constants.StaticValues
import com.example.retrofitmovieskotlin.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setMovieInfo()
    }

    private fun setMovieInfo() {
        val movie = intent.getParcelableExtra<Movie>(StaticValues.MOVIE_KEY)
        detailMovieTitle.text = movie.title
        detailMovieGenre.text = movie.genre?.joinToString(prefix = "[",
            separator = ", ", postfix = "]")
        detailMovieRating.text = movie.rating.toString()
        detailMovieReleaseYear.text = movie.releaseYear.toString()
        Picasso.with(this).load(movie.image).into(detailMovieImage)
    }
}
