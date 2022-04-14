package com.ujanglukmanbdg.githubtoday.activity

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ujanglukmanbdg.githubtoday.MainActivity
import com.ujanglukmanbdg.githubtoday.R
import com.ujanglukmanbdg.githubtoday.adapter.ListSearchUserAdapter
import com.ujanglukmanbdg.githubtoday.data.ItemsItem
import com.ujanglukmanbdg.githubtoday.databinding.ActivitySearchUserBinding
import com.ujanglukmanbdg.githubtoday.helper.Status
import com.ujanglukmanbdg.githubtoday.viewmodel.SearchViewModel

class SearchUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchUserBinding

    private lateinit var adapter: ListSearchUserAdapter
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.result_user)

        val fab: FloatingActionButton = findViewById(R.id.fab_home)
        val moveIntentHome = Intent(this, MainActivity::class.java)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Anda akan kembali ke Menu Home", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            startActivity(moveIntentHome)
        }

        initRecyclerView()
        initViewModel()
        hideLoading()

    }

    private fun initViewModel() {
        searchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[SearchViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView() {
        adapter = ListSearchUserAdapter()
        adapter.notifyDataSetChanged()
        val rvSearchUser = binding.rvResultSearchUser
        rvSearchUser.layoutManager = LinearLayoutManager(this)
        rvSearchUser.adapter = adapter
        rvSearchUser.setHasFixedSize(false)
        adapter.setOnItemClickCallback(object : ListSearchUserAdapter.OnItemClickCallback{
            override fun onItemClicked(user: ItemsItem) {
                val intentToDetail = Intent(this@SearchUserActivity, DetailSearchUserActivity::class.java)
                intentToDetail.putExtra(DetailSearchUserActivity.EXTRA_DETAIL_USERNAME, user)
                startActivity(intentToDetail)
                Toast.makeText(this@SearchUserActivity, user.login, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@SearchUserActivity, query, Toast.LENGTH_SHORT).show()
                if (query != null) {
                    if (query.isNotEmpty()) {
                        showLoading()
                        searchViewModel.setSearchResult(query).observe(this@SearchUserActivity){
                            when(it.status){
                                Status.LOADING -> showLoading()
                                Status.SUCCESS -> {
                                    it.data?.let { users -> adapter.setUser(users) }
                                    hideLoading()
                                    closeKeyBoard()
                                }
                                Status.ERROR -> {
                                    hideLoading()
                                    closeKeyBoard()
                                    Toast.makeText(this@SearchUserActivity, "Failure: ${it.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun hideLoading() {
        binding.apply {
            progressBar.visibility = View.GONE
            rvResultSearchUser.visibility = View.VISIBLE
        }
    }

    private fun showLoading() {
        binding.apply {
            rvResultSearchUser.visibility = View.GONE
            tvSearchDescription.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }
}