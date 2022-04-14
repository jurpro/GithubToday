package com.ujanglukmanbdg.githubtoday.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ujanglukmanbdg.githubtoday.data.ItemsItem

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: ItemsItem)

    @Update
    fun update(user: ItemsItem)

    @Delete
    fun delete(user: ItemsItem)

    @Query("SELECT * from user ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<ItemsItem>>

    @Query("SELECT * from user WHERE login = :username")
    fun getFavoriteByUsername(username: String): LiveData<List<ItemsItem>>

}