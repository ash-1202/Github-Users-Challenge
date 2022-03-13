package com.jodel.githubusers.api

import com.jodel.githubusers.api.models.GetGithubUserResponseModel
import com.jodel.githubusers.api.models.GithubUserModel
import retrofit2.Response
import retrofit2.http.*

interface GithubApi {

    @GET("search/users?q=repos:>1")
    suspend fun getUsersList(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Response<GetGithubUserResponseModel>

    @GET("users/{username}")
    suspend fun getUserInfo(
        @Path("username") username: String
    ): Response<GithubUserModel>
}