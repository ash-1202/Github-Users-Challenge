package com.jodel.githubusers.modules

import com.jodel.githubusers.ui.datasource.UsersListDataSourceFactory
import org.koin.dsl.module

val usersListDataSourceFactory = module {
    single { UsersListDataSourceFactory(get()) }
}