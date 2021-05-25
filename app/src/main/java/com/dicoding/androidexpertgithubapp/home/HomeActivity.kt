package com.dicoding.androidexpertgithubapp.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.androidexpertgithubapp.R
import com.dicoding.androidexpertgithubapp.core.data.Resource
import com.dicoding.androidexpertgithubapp.core.ui.MovieAdapter
import com.dicoding.androidexpertgithubapp.databinding.ActivityHomeBinding
import com.dicoding.androidexpertgithubapp.detail.MovieDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeAdapter = MovieAdapter()
        homeAdapter.onItemClick = { movie ->
            val intent = Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
            }
            startActivity(intent)
        }

        homeViewModel.movieList.observe(this, { movie ->
            if (movie != null) {
                when(movie) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        homeAdapter.setMovieList(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@HomeActivity, "Error on Loading Data", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, movie.message?: "null")
                    }
                }
            }
        })

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = homeAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_show_fav -> {
                val uri = Uri.parse("androidexpertgithubapp://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = HomeActivity::class.java.simpleName
    }
}