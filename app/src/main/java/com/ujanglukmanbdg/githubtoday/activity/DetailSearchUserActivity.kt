package com.ujanglukmanbdg.githubtoday.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ujanglukmanbdg.githubtoday.MainActivity
import com.ujanglukmanbdg.githubtoday.R
import com.ujanglukmanbdg.githubtoday.adapter.SectionsPagerAdapter
import com.ujanglukmanbdg.githubtoday.data.ItemsItem
import com.ujanglukmanbdg.githubtoday.data.ResponseDetailUser
import com.ujanglukmanbdg.githubtoday.databinding.ActivityDetailSearchUserBinding
import com.ujanglukmanbdg.githubtoday.helper.Status
import com.ujanglukmanbdg.githubtoday.ui.favorite.FavoriteViewModelFactory
import com.ujanglukmanbdg.githubtoday.viewmodel.UserDetailViewModel

class DetailSearchUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSearchUserBinding
    private lateinit var viewModel: UserDetailViewModel

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_search_user)
        binding = ActivityDetailSearchUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = resources.getString(R.string.detail_search_user)
        }

        val user = intent.getParcelableExtra<ItemsItem>(EXTRA_DETAIL_USERNAME)

        initViewModel()

        if (user != null) {
            viewModel.getUserResult(user.login).observe(this) {
                when (it.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        it.data?.let { user -> loadData(user) }
                        showLoading(false)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(this, "Failure: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        buttonChecked()
        if (user != null) {
            viewModel.getFavoriteUsername(user.login).observe(this){
                if (it.isNotEmpty()) {
                    isFavorite = true
                    buttonChecked()
                }
            }
        }

        binding.fabFavorite.setOnClickListener {
            isFavorite = !isFavorite
            when (isFavorite) {
                true -> {
                    user?.let { user -> viewModel.insertUsername(user) }
                    buttonChecked()
                }
                false -> {
                    user?.let { user -> viewModel.deleteUsername(user) }
                    buttonChecked()
                }
            }
        }

        val fab: FloatingActionButton = findViewById(R.id.fab_home)
        val moveIntentHome = Intent(this, MainActivity::class.java)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Anda akan kembali ke Menu Home", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            startActivity(moveIntentHome)
        }
    }

    private fun buttonChecked() {
        if (isFavorite) {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_full)
            Toast.makeText(this, getString(R.string.added_to_favorite), Toast.LENGTH_SHORT).show()
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun initViewModel() {
        val factory = FavoriteViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[UserDetailViewModel::class.java]
    }

    private fun loadData(data: ResponseDetailUser) {

        binding.apply {
            Glide.with(this@DetailSearchUserActivity)
                .load(data.avatarUrl)
                .circleCrop()
                .into(includeHeaderDetail.imgAvatarUserDetail)
            includeHeaderDetail.tvNameUserDetail.text = data.name
            includeHeaderDetail.tvUsernameUserDetail.text = data.login
            includeHeaderDetail.textSearchFollowers.text = data.followers.toString()
            includeHeaderDetail.textSearchFollowing.text = data.following.toString()
            includeHeaderDetail.textSearchRepository.text = data.publicRepos.toString()
            includeDescriptionDetail.textEmailDetail.text = data.email.toString()
            includeDescriptionDetail.textWebsiteDetail.text = data.blog.toString()
            includeDescriptionDetail.textTwitterDetail.text = data.twitterUsername.toString()
            includeDescriptionDetail.textLocationDetail.text = data.location.toString()
            includeDescriptionDetail.textActiveDetail.text = data.createdAt
            includeDescriptionDetail.textLastUpdateDetail.text = data.updatedAt
            includeDescriptionDetail.textCompanyDetail.text = data.company.toString()
            includeDescriptionDetail.textAboutUserDetail.text = data.bio.toString()

            val sectionsPagerAdapter = SectionsPagerAdapter(this@DetailSearchUserActivity)
            val viewPagerDetail: ViewPager2 = findViewById(R.id.view_pager_detail)
            val tabList: TabLayout = findViewById(R.id.tabs_detail)

            viewPagerDetail.adapter = sectionsPagerAdapter

            TabLayoutMediator(tabList, viewPagerDetail) { tab, position ->
                tab.text = tabList.resources.getString(TAB_TITLES[position])
            }.attach()

            supportActionBar?.elevation = 0f

        }
        showLoading(false)
    }

    private fun showLoading(loading: Boolean) {
        binding.apply {
            if (loading) {
                progressBarDetail.visibility = View.VISIBLE
                appBar.visibility = View.GONE
                tabsDetail.visibility = View.GONE
                viewPagerDetail.visibility = View.GONE
                fabFavorite.visibility = View.GONE
                fabHome.visibility = View.VISIBLE
            } else {
                progressBarDetail.visibility = View.GONE
                appBar.visibility = View.VISIBLE
                tabsDetail.visibility = View.VISIBLE
                viewPagerDetail.visibility = View.VISIBLE
                fabFavorite.visibility = View.VISIBLE
                fabHome.visibility = View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    companion object {
        fun start(activity: FragmentActivity?, user: ItemsItem) {
            val intent = Intent(activity, DetailSearchUserActivity::class.java)
            intent.putExtra(EXTRA_DETAIL_USERNAME, user)
            activity?.startActivity(intent)
        }

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )

        const val EXTRA_DETAIL_USERNAME = "extra_detail_username"

    }
}
