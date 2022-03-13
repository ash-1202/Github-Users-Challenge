package com.jodel.githubusers.modules

import com.jodel.githubusers.ui.viewmodel.SingleUserViewModel
import com.jodel.githubusers.ui.viewmodel.UsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
    
val usersListViewModel = module {
    viewModel { UsersListViewModel(get()) }
}

val singleUserViewModel = module {
    viewModel { SingleUserViewModel(get()) }
}