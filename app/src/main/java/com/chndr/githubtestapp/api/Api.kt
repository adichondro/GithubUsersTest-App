package com.chndr.githubtestapp.api

import com.chndr.githubtestapp.BuildConfig
import com.chndr.githubtestapp.data.model.DetailUserResponse
import com.chndr.githubtestapp.data.model.ReposUserResponse
import com.chndr.githubtestapp.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {
    companion object{
        const val tokengit = BuildConfig.TOKEN_GIT
    }

    @GET("search/users")
    @Headers("Authorization: token $tokengit")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token $tokengit")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/repos")
    @Headers("Authorization: token $tokengit")
    fun getRepos(
        @Path("username") username: String
    ): Call<ArrayList<ReposUserResponse>>
}