package com.ujanglukmanbdg.githubtoday.activity

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ujanglukmanbdg.githubtoday.R
import com.ujanglukmanbdg.githubtoday.adapter.ListGithubUserAdapter
import com.ujanglukmanbdg.githubtoday.data.GithubUser

class ListUserActivity : AppCompatActivity() {
    private lateinit var rvGithubUser: RecyclerView
    private val list = ArrayList<GithubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)

        supportActionBar?.title = resources.getString(R.string.github_user_list_title)

        rvGithubUser = findViewById(R.id.rv_githubuser)
        rvGithubUser.setHasFixedSize(true)

        list.addAll(listGithubUsers)
        showRecyclerList()
    }

    private val listGithubUsers: ArrayList<GithubUser>
    get() {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataLocation = resources.getStringArray(R.array.data_location)
        val dataActiveSince = resources.getStringArray(R.array.data_since)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataUsername = resources.getStringArray(R.array.data_username)
        val dataRepository = resources.getIntArray(R.array.data_repository)
        val dataCompany = resources.getStringArray(R.array.data_company)
        val dataFollowers = resources.getIntArray(R.array.data_followers)
        val dataFollowing = resources.getIntArray(R.array.data_following)
        val dataEmail = resources.getStringArray(R.array.data_email)
        val dataAboutUser = resources.getStringArray(R.array.data_about_user)
        val dataWebsite = resources.getStringArray(R.array.data_website)
        val dataTwitter = resources.getStringArray(R.array.data_twitter)
        val dataProject = resources.getStringArray(R.array.data_project)
        val dataPackage = resources.getStringArray(R.array.data_packageGithub)
        val dataStarGithub = resources.getIntArray(R.array.data_starsGithub)
        val dataLanguageGithub = resources.getStringArray(R.array.data_languageGithub)
        val dataAchievements = resources.getStringArray(R.array.data_achievements)


        val listGithubUser = ArrayList<GithubUser>()
        for (i in dataName.indices) {
            val githubUser = GithubUser(
                dataName[i],
                dataLocation[i],
                dataActiveSince[i],
                dataPhoto[i],
                dataUsername[i],
                dataRepository[i],
                dataCompany[i],
                dataFollowers[i],
                dataFollowing[i],
                dataEmail[i],
                dataAboutUser[i],
                dataWebsite[i],
                dataTwitter[i],
                dataProject[i],
                dataPackage[i],
                dataStarGithub[i],
                dataLanguageGithub[i],
                dataAchievements[i]
            )
            listGithubUser.add(githubUser)
        }
        return listGithubUser
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvGithubUser.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvGithubUser.layoutManager = LinearLayoutManager(this)
        }

        val listGithubUserAdapter = ListGithubUserAdapter(list)
        rvGithubUser.adapter = listGithubUserAdapter

        listGithubUserAdapter.setOnItemClickCallback(object : ListGithubUserAdapter.OnItemClickCallback {
            override fun onItemClicked(datagithub: GithubUser) {
                Intent(this@ListUserActivity, UserDetailActivity::class.java).apply {
                    putExtra(UserDetailActivity.EXTRA_USER, datagithub)
                }
            }
        })
    }

}