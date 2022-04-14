package com.ujanglukmanbdg.githubtoday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ujanglukmanbdg.githubtoday.data.ItemsItem
import com.ujanglukmanbdg.githubtoday.helper.RemoteRepository
import com.ujanglukmanbdg.githubtoday.helper.Resource

class FollowersViewModel: ViewModel() {
    private val repository: RemoteRepository = RemoteRepository()

    fun getFollowers(username: String): LiveData<Resource<ArrayList<ItemsItem>>> {
        return repository.getDetailFollowers(username)
    }

    fun getFollowing(username: String): LiveData<Resource<ArrayList<ItemsItem>>> {
        return repository.getDetailFollowing(username)
    }

}