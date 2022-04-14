package com.ujanglukmanbdg.githubtoday.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseSearchUser(

	@field:SerializedName("items")
	val items: ArrayList<ItemsItem>,

	@field:SerializedName("total_count")
	val totalCount: Int?,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean?,
)

@Entity( "user")
@Parcelize
data class ItemsItem(

	@PrimaryKey(autoGenerate = false)
	@ColumnInfo("login")
	@field:SerializedName("login")
	val login: String,

	@ColumnInfo("followers_url")
	@field:SerializedName("followers_url")
	val followersUrl: String? = null,

	@ColumnInfo("following_url")
	@field:SerializedName("following_url")
	val followingUrl: String? = null,

	@ColumnInfo("avatar_url")
	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@ColumnInfo("gists_url")
	@field:SerializedName("gists_url")
	val gistsUrl: String? = null,

	@ColumnInfo("repos_url")
	@field:SerializedName("repos_url")
	val reposUrl: String? = null,

	@ColumnInfo("starred_url")
	@field:SerializedName("starred_url")
	val starredUrl: String? = null,

	@ColumnInfo("type")
	@field:SerializedName("type")
	val type: String? = null,

	@ColumnInfo("url")
	@field:SerializedName("url")
	val url: String? = null,

	@ColumnInfo("subscriptions_url")
	@field:SerializedName("subscriptions_url")
	val subscriptionsUrl: String? = null,

	@ColumnInfo("score")
	@field:SerializedName("score")
	val score: Double? = null,

	@ColumnInfo("received_events_url")
	@field:SerializedName("received_events_url")
	val receivedEventsUrl: String? = null,

	@ColumnInfo("events_url")
	@field:SerializedName("events_url")
	val eventsUrl: String? = null,

	@ColumnInfo("html_url")
	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@ColumnInfo("site_admin")
	@field:SerializedName("site_admin")
	val siteAdmin: Boolean? = null,

	@ColumnInfo("id")
	@field:SerializedName("id")
	val id: Int? = null,

	@ColumnInfo("gravatar_id")
	@field:SerializedName("gravatar_id")
	val gravatarId: String? = null,

	@ColumnInfo("node_id")
	@field:SerializedName("node_id")
	val nodeId: String? = null,

	@ColumnInfo("organizations_url")
	@field:SerializedName("organizations_url")
	val organizationsUrl: String? = null
): Parcelable
