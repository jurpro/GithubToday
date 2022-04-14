package com.ujanglukmanbdg.githubtoday.helper

import android.app.Application
import androidx.lifecycle.LiveData
import com.ujanglukmanbdg.githubtoday.data.ItemsItem
import com.ujanglukmanbdg.githubtoday.database.FavoriteDao
import com.ujanglukmanbdg.githubtoday.database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorites(): LiveData<List<ItemsItem>> {
        return mFavoriteDao.getAllFavorite()
    }

    fun getFavoriteByUsername(username: String): LiveData<List<ItemsItem>> {
        return mFavoriteDao.getFavoriteByUsername(username)
    }

    fun insert(user: ItemsItem) {
        executorService.execute {mFavoriteDao.insert(user)}
    }

    fun delete(user: ItemsItem) {
        executorService.execute {mFavoriteDao.delete(user)}
    }

    fun update(user: ItemsItem) {
        executorService.execute {mFavoriteDao.update(user)}
    }
}