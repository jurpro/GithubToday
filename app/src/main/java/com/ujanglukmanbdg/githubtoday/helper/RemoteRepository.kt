package com.ujanglukmanbdg.githubtoday.helper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ujanglukmanbdg.githubtoday.api.ApiConfig
import com.ujanglukmanbdg.githubtoday.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {
    private val apiGithubClient = ApiConfig.getApiService()

    fun loadResultSearch(username: String): LiveData<Resource<ArrayList<ItemsItem>>> {
        val users = MutableLiveData<Resource<ArrayList<ItemsItem>>>()
        apiGithubClient.getSearchUser(username).enqueue(object :
        Callback<ResponseSearchUser> {
            override fun onResponse(
                call: Call<ResponseSearchUser>,
                response: Response<ResponseSearchUser>
            ) {
                users.value = Resource.loading()
                if (response.isSuccessful) {
                    response.body().let {
                        users.value = Resource.success(response.body()?.items)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSearchUser>, t: Throwable) {
                users.value = Resource.error(t.message.toString())
            }
        })
        return users
    }

    fun getUserDetail(username: String): LiveData<Resource<ResponseDetailUser>> {
        val user = MutableLiveData<Resource<ResponseDetailUser>>()
        apiGithubClient.getUsername(username).enqueue(object :
        Callback<ResponseDetailUser> {
            override fun onResponse(
                call: Call<ResponseDetailUser>,
                response: Response<ResponseDetailUser>
            ) {
                user.value = Resource.loading()
                if (response.isSuccessful) {
                    response.body().let {
                        user.value = Resource.success(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDetailUser>, t: Throwable) {
                user.value = Resource.error(t.message.toString())
            }
        })
        return user
    }

    fun getDetailFollowers(username: String): LiveData<Resource<ArrayList<ItemsItem>>> {
        val followers = MutableLiveData<Resource<ArrayList<ItemsItem>>>()
        apiGithubClient.getFollowers(username).enqueue(object :
        Callback<ArrayList<ItemsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ItemsItem>>,
                response: Response<ArrayList<ItemsItem>>
            ) {
                followers.value = Resource.loading()
                if (response.isSuccessful) {
                    response.body().let {
                        followers.value = Resource.success(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                followers.value = Resource.error(t.message.toString())
            }
        })
        return followers
    }

    fun getDetailFollowing(username: String): LiveData<Resource<ArrayList<ItemsItem>>> {
        val following = MutableLiveData<Resource<ArrayList<ItemsItem>>>()
        apiGithubClient.getFollowing(username).enqueue(object :
        Callback<ArrayList<ItemsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ItemsItem>>,
                response: Response<ArrayList<ItemsItem>>
            ) {
                following.value = Resource.loading()
                if (response.isSuccessful) {
                    response.body().let {
                        following.value = Resource.success(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                following.value = Resource.error(t.message.toString())
            }
        })
        return following
    }
}