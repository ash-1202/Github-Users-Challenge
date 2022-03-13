package com.jodel.githubusers.ui.datasource

import androidx.paging.DataSource
import androidx.lifecycle.MutableLiveData
import com.jodel.githubusers.api.client.GithubApiClient
import com.jodel.githubusers.api.models.GithubUserModel

class UsersListDataSourceFactory(private val githubApiClient: GithubApiClient): DataSource.Factory<Int, GithubUserModel>() {

    val liveData: MutableLiveData<UsersListDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, GithubUserModel> {
        val usersListDataSource = UsersListDataSource(githubApiClient)
        liveData.postValue(usersListDataSource)
        return usersListDataSource
    }
}