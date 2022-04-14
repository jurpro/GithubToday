package com.ujanglukmanbdg.githubtoday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ujanglukmanbdg.githubtoday.data.ItemsItem
import com.ujanglukmanbdg.githubtoday.helper.RemoteRepository
import com.ujanglukmanbdg.githubtoday.helper.Resource

class SearchViewModel: ViewModel() {
    private val repository: RemoteRepository = RemoteRepository()

    fun setSearchResult(username: String): LiveData<Resource<ArrayList<ItemsItem>>> {
        return repository.loadResultSearch(username)
    }
}