package com.ujanglukmanbdg.githubtoday.api

import com.ujanglukmanbdg.githubtoday.BuildConfig.API_KEY_GITHUB
import com.ujanglukmanbdg.githubtoday.data.*

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token $API_KEY_GITHUB")
    @GET("search/users")
    fun getSearchUser(
        @Query("q") username: String
    ): Call<ResponseSearchUser>

    @Headers("Authorization: token $API_KEY_GITHUB")
    @GET("users/{username}")
    fun getUsername(
        @Path("username") username: String
    ): Call<ResponseDetailUser>

    @Headers("Authorization: token $API_KEY_GITHUB")
    @GET("users/{username}/followers")
    fun getFollowers (
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>

    @Headers("Authorization: token $API_KEY_GITHUB")
    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>
}