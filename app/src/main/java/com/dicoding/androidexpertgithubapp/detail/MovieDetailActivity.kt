package com.dicoding.androidexpertgithubapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.androidexpertgithubapp.R
import com.dicoding.androidexpertgithubapp.core.domain.model.Movie
import com.dicoding.androidexpertgithubapp.databinding.ActivityMovieDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {
    private val movieDetailViewModel: MovieDetailViewModel by viewModel()
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieExtra = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        setUserDetail(movieExtra)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setUserDetail(movie: Movie?){
        movie?.let {
            supportActionBar?.title = movie.title
            with(binding){
                Glide.with(this@MovieDetailActivity)
                    .load(getString(R.string.image_link, movie.posterPath))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_image_24).error(R.drawable.ic_baseline_broken_image_24))
                    .into(binding.posterImg)
                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate
                movieRating.text = getString(R.string.rating, movie.voteAverage.toString())
                movieVoter.text = getString(R.string.voter_count, movie.voteCount.toString())
                movieOverview.text = movie.overview
                var isFav = movie.isFavorite
                setFavButtonImage(isFav)
                binding.favButton.setOnClickListener{
                    isFav = !isFav
                    movieDetailViewModel.setMovieFavoriteStatus(movie, isFav)
                    setFavButtonImage(isFav)
                    displayToast(isFav)
                }
            }
        }
    }

    private fun setFavButtonImage(isFavorite: Boolean){
        if(isFavorite){
            binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun displayToast(isFavorite: Boolean){
        if(isFavorite){
            Toast.makeText(this@MovieDetailActivity, getString(R.string.faving_movie), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MovieDetailActivity, getString(R.string.unfaving_movie), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

}