package com.example.gymapp.ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.dao.PaymentDao

class PaymentViewModelFactory(private val dao: PaymentDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaymentViewModel::class.java))
            return PaymentViewModel(dao) as T

        throw IllegalArgumentException("Unknown viewModel")
    }
}