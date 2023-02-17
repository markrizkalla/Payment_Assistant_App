package com.example.gymapp.ui.detail

import androidx.lifecycle.ViewModel
import com.example.gymapp.database.SubscribersDao

class DetailViewModel(subscribersDao: SubscribersDao) :ViewModel() {

    var users = subscribersDao.getAll()
}