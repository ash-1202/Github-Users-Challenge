package com.jodel.githubusers.api.client

import com.jodel.githubusers.api.base.Resource
import com.jodel.githubusers.api.models.GetGithubUserResponseModel
import com.jodel.githubusers.api.models.GithubUserModel


interface GithubApiClient {

    suspend fun getUsersList(page: Int, pageSize: Int): Resource<GetGithubUserResponseModel>

    suspend fun getUserInfo(username: String): Resource<GithubUserModel>
}