package com.ujanglukmanbdg.githubtoday.ui.favorite

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ujanglukmanbdg.githubtoday.MainActivity
import com.ujanglukmanbdg.githubtoday.R
import com.ujanglukmanbdg.githubtoday.activity.DetailSearchUserActivity
import com.ujanglukmanbdg.githubtoday.adapter.ListSearchUserAdapter
import com.ujanglukmanbdg.githubtoday.data.ItemsItem
import com.ujanglukmanbdg.githubtoday.databinding.ActivityMyFavoriteBinding

class MyFavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: ListSearchUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.favorite)

        val fab: FloatingActionButton = findViewById(R.id.fab_home)
        val moveIntentHome = Intent(this, MainActivity::class.java)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Anda akan kembali ke Menu Home", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            startActivity(moveIntentHome)
        }

        initRecyclerView()
        initViewModel()

        viewModel.getAllFavoritesDatabase().observe(this) {
            adapter.setUser(it as ArrayList<ItemsItem>)
        }

    }

    private fun initViewModel() {
        val factory = FavoriteViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView() {
        adapter = ListSearchUserAdapter()
        adapter.notifyDataSetChanged()
        val rvFavorites = binding.rvFavorites
        rvFavorites.layoutManager = LinearLayoutManager(this)
        rvFavorites.adapter = adapter
        rvFavorites.setHasFixedSize(false)
        adapter.setOnItemClickCallback(object : ListSearchUserAdapter.OnItemClickCallback{
            override fun onItemClicked(user: ItemsItem) {
                val intentToDetail = Intent(this@MyFavoriteActivity, DetailSearchUserActivity::class.java)
                intentToDetail.putExtra(DetailSearchUserActivity.EXTRA_DETAIL_USERNAME, user)
                startActivity(intentToDetail)
                Toast.makeText(this@MyFavoriteActivity, user.login, Toast.LENGTH_SHORT).show()
            }
        })

        binding.apply {
            progressBar.visibility = View.GONE
            tvFavoriteDescription.visibility = View.GONE
            rvFavorites.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_main_drawer, menu)

        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        fun start(context: Activity?) {
            val intent = Intent(context, MyFavoriteActivity::class.java)
            context?.startActivity(intent)
        }
    }
}