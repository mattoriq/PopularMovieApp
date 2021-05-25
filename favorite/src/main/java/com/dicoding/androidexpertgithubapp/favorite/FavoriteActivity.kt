package com.dicoding.androidexpertgithubapp.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.androidexpertgithubapp.R
import com.dicoding.androidexpertgithubapp.core.ui.MovieAdapter
import com.dicoding.androidexpertgithubapp.detail.MovieDetailActivity
import com.dicoding.androidexpertgithubapp.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.fav_movie)

        loadKoinModules(favoriteModule)

        val favoriteAdapter = MovieAdapter()
        favoriteAdapter.onItemClick = { movie ->
            val intent = Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
            }
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovie.observe(this, { favMovie ->
            favoriteAdapter.setMovieList(favMovie)
            binding.favIsEmpty.visibility = if(favMovie.isEmpty()) View.VISIBLE else View.GONE
        })

        with(binding.rvFavMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}