package com.ujanglukmanbdg.githubtoday.helper

import androidx.recyclerview.widget.DiffUtil
import com.ujanglukmanbdg.githubtoday.data.ItemsItem

class FavoriteDiffCallback(private val mOldFavoriteList: List<ItemsItem>, private val mNewFavoriteList: List<ItemsItem>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavoriteList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavoriteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavoriteList[oldItemPosition].id == mNewFavoriteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUsername = mOldFavoriteList[oldItemPosition]
        val newUsername = mNewFavoriteList[newItemPosition]
        return oldUsername.login == newUsername.login && oldUsername.avatarUrl == newUsername.avatarUrl
    }
}