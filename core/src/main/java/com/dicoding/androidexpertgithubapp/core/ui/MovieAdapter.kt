package com.dicoding.androidexpertgithubapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.androidexpertgithubapp.core.R
import com.dicoding.androidexpertgithubapp.core.databinding.UserListViewBinding
import com.dicoding.androidexpertgithubapp.core.domain.model.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movieList = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserListViewBinding.bind(itemView)
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.image_link, movie.posterPath))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_image_24).error(R.drawable.ic_baseline_broken_image_24))
                    .into(binding.imgPoster)
                movieTitle.text = movie.title
                movieReleaseDate.text = itemView.context.getString(R.string.released_on, movie.releaseDate)
                movieDetail.text = movie.overview
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(movieList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_list_view, parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieData = movieList[position]
        holder.bind(movieData)
    }

    override fun getItemCount(): Int = movieList.size

    fun setMovieList(newMovieList: List<Movie>?) {
        if (newMovieList == null) return
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }
}