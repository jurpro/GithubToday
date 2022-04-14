package com.ujanglukmanbdg.githubtoday.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ujanglukmanbdg.githubtoday.data.ItemsItem
import com.ujanglukmanbdg.githubtoday.data.ResponseDetailUser
import com.ujanglukmanbdg.githubtoday.helper.FavoriteRepository
import com.ujanglukmanbdg.githubtoday.helper.RemoteRepository
import com.ujanglukmanbdg.githubtoday.helper.Resource

class UserDetailViewModel(application: Application): ViewModel() {
    private val repository: RemoteRepository = RemoteRepository()
    private val favoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getUserResult(username: String): LiveData<Resource<ResponseDetailUser>> {
        return repository.getUserDetail(username)
    }

    fun getFavoriteUsername(username: String): LiveData<List<ItemsItem>> {
        return favoriteRepository.getFavoriteByUsername(username)
    }

    fun insertUsername(user: ItemsItem) {
        return favoriteRepository.insert(user)
    }

    fun deleteUsername(user: ItemsItem) {
        return favoriteRepository.delete(user)
    }
}