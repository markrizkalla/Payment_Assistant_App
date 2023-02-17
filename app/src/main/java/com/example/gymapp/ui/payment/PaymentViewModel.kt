package com.example.gymapp.ui.payment

import androidx.lifecycle.ViewModel
import com.example.gymapp.dao.PaymentDao

class PaymentViewModel(val paymentDao: PaymentDao) : ViewModel() {

    val payments = paymentDao.getAll()
}