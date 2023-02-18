package com.example.gymapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.dao.PaymentDao
import com.example.gymapp.dao.SubscribersDao

class DetailViewModelFactory(private val subscribersDao: SubscribersDao,private val paymentDao: PaymentDao,private val id :Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(subscribersDao,paymentDao,id) as T
        }
        throw IllegalArgumentException("Unknown viewModel")
    }
}