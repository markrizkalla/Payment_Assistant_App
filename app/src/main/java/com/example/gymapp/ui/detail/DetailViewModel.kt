package com.example.gymapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.dao.PaymentDao
import com.example.gymapp.dao.SubscribersDao
import com.example.gymapp.model.Payment
import com.example.gymapp.model.Subscriber
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailViewModel(val subscribersDao: SubscribersDao,val  paymentDao: PaymentDao, var id:Int) :ViewModel() {

    val _edit = MutableLiveData<Boolean>(false)
    private val edit : LiveData<Boolean> get() = _edit

    var subscriber = subscribersDao.get(id)
    var payment = paymentDao.get(id)

    fun update(name:String, startDate:String,endDate:String,price :String){
        if (edit.value == true){
            viewModelScope.launch {
                subscriber.value?.name = name
                subscriber.value?.subDate = startDate
                subscriber.value?.sebEndDate = endDate
                subscriber.value?.subPrice = price

                subscribersDao.update(subscriber.value!!)
            }

            viewModelScope.launch {
                payment.value?.name = name
                payment.value?.subDate = startDate
                payment.value?.subPrice = price
                paymentDao.update(payment.value!!)
            }
        }else {
            val newSubscriber =
                Subscriber(name = name, sebEndDate = endDate, subDate = startDate, subPrice = price)
            val newPayment = Payment(
                subscriber_id = newSubscriber.subscriber_id,
                name = name,
                subDate = startDate,
                subPrice = price
            )
            viewModelScope.launch {
                subscribersDao.insert(newSubscriber)
            }
            viewModelScope.launch {
                paymentDao.insert(newPayment)
            }
        }
    }

}