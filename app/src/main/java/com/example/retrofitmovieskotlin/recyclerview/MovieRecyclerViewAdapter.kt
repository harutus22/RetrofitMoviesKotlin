package com.example.retrofitmovieskotlin.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmovieskotlin.R
import com.example.retrofitmovieskotlin.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_movie.view.*

class MovieRecyclerViewAdapter: ListAdapter<Movie, MovieRecyclerViewAdapter.MovieViewHolder>(DiffCallback()) {
    lateinit var onMovieClickListener: OnMovieClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(movie: Movie){
            itemView.movieTitle.text = movie.title
            Picasso.with(itemView.context).load(movie.image).into(itemView.movieImage)
            itemView.movieRating.text = movie.rating.toString()
            itemView.movieReleaseYear.text = movie.releaseYear.toString()
            itemView.setOnClickListener {
                onMovieClickListener.onMovieClicked(movie)
            }
        }
    }

    companion object{
        class DiffCallback: DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.image == newItem.image &&
                        oldItem.rating == newItem.rating &&
                        oldItem.releaseYear == newItem.releaseYear &&
                        oldItem.genre!!.equals(newItem.genre)
            }

        }
    }

    interface OnMovieClickListener{
        fun onMovieClicked(movie: Movie)
    }
}