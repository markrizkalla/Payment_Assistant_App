package com.example.gymapp.ui.home

import androidx.lifecycle.ViewModel
import com.example.gymapp.dao.SubscribersDao

class HomeViewModel(dao: SubscribersDao):ViewModel() {

    var subLists = dao.getAll()
}