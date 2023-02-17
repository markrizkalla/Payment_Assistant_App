package com.example.gymapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.dao.SubscribersDao

class HomeViewModelFactory(private val dao: SubscribersDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unkown ViewModel")
    }
}