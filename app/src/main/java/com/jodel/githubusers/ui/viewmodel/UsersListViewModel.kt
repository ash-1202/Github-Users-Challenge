package com.jodel.githubusers.ui.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jodel.githubusers.api.models.GithubUserModel
import com.jodel.githubusers.ui.datasource.UsersListDataSource
import com.jodel.githubusers.ui.datasource.UsersListDataSourceFactory
import java.util.concurrent.Executors

class UsersListViewModel(private val usersListDataSourceFactory: UsersListDataSourceFactory) : ViewModel() {

    var dataSource: MutableLiveData<UsersListDataSource>
    lateinit var usersLiveData: LiveData<PagedList<GithubUserModel>>
    val isWaiting: ObservableField<Boolean> = ObservableField()
    val errorMessage: ObservableField<String> = ObservableField()
    val totalCount: ObservableField<Long> = ObservableField()

    init {
        isWaiting.set(true)
        errorMessage.set(null)
        dataSource = usersListDataSourceFactory.liveData
        initUsersListFactory()
    }

    private fun initUsersListFactory() {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(UsersListDataSource.PAGE_SIZE)
            .setPageSize(UsersListDataSource.PAGE_SIZE)
            .setPrefetchDistance(3)
            .build()

        val executor = Executors.newFixedThreadPool(5)

        usersLiveData = LivePagedListBuilder<Int, GithubUserModel>(usersListDataSourceFactory, config)
            .setFetchExecutor(executor)
            .build()
    }
}
