package com.ujanglukmanbdg.githubtoday

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListUserActivity : AppCompatActivity() {
    private lateinit var rvGithubUser: RecyclerView
    private val list = ArrayList<GithubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)

        rvGithubUser = findViewById(R.id.rv_githubuser)
        rvGithubUser.setHasFixedSize(true)

        list.addAll(listGithubUsers)
        showRecyclerList()
    }

    private val listGithubUsers: ArrayList<GithubUser>
    get() {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_about_user)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listGithubUser = ArrayList<GithubUser>()
        for (i in dataName.indices) {
            val githubUser = GithubUser(dataName[i],dataDescription[i],dataPhoto.getResourceId(i, -1))
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

    }
}
