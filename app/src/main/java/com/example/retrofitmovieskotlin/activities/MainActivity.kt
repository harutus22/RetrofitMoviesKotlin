package com.example.retrofitmovieskotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitmovieskotlin.R
import com.example.retrofitmovieskotlin.constants.StaticValues
import com.example.retrofitmovieskotlin.model.Movie
import com.example.retrofitmovieskotlin.recyclerview.MovieRecyclerViewAdapter
import com.example.retrofitmovieskotlin.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieRecyclerViewAdapter.OnMovieClickListener {
    private lateinit var adapter: MovieRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createViewModel()
        createRecyclerView()
    }

    private fun createRecyclerView() {
        adapter = MovieRecyclerViewAdapter()
        adapter.onMovieClickListener = this
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun createViewModel(){
        val movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.movieLiveData.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun onMovieClicked(movie: Movie) {
        val toDetailMovie = Intent(this, DetailMovieActivity::class.java)
        toDetailMovie.putExtra(StaticValues.MOVIE_KEY, movie)
        startActivity(toDetailMovie)
    }
}
