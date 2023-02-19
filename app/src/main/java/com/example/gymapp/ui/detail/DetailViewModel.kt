package com.example.gymapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.dao.PaymentDao
import com.example.gymapp.dao.SubscribersDao
import com.example.gymapp.model.Payment
import com.example.gymapp.model.Subscriber
import com.example.gymapp.model.SubscribersWithPayments
import kotlinx.coroutines.*

class DetailViewModel(val subscribersDao: SubscribersDao,val  paymentDao: PaymentDao, var id:Int) :ViewModel() {


    private val _navigateToHome = MutableLiveData<Boolean>(false)
    val navigateToHome: LiveData<Boolean>  get() = _navigateToHome

    val _edit = MutableLiveData<Boolean>(false)
    private val edit : LiveData<Boolean> get() = _edit

    var subscriber = subscribersDao.get(id)
    //var payment = paymentDao.get(id)

    private var _history = MutableLiveData<SubscribersWithPayments?>()
    val history : LiveData<SubscribersWithPayments?> get() = _history

    fun update(name:String, startDate:String,endDate:String,price :String){
        if (edit.value == true){
            viewModelScope.launch {
                subscriber.value?.name = name
                subscriber.value?.subDate = startDate
                subscriber.value?.sebEndDate = endDate
                subscriber.value?.subPrice = price

                subscribersDao.update(subscriber.value!!)
            }

        }else {
            val newSubscriber =
                Subscriber(name = name, sebEndDate = endDate, subDate = startDate, subPrice = price)

            runBlocking{
                 subscribersDao.insert(newSubscriber)
                val newPayment = Payment(
                    subscriber_id = newSubscriber.subscriber_id,
                    name = name,
                    subDate = startDate,
                    subPrice = price
                )
                paymentDao.insert(newPayment)
            }
        }
    }

    fun delete(){
        viewModelScope.launch {
            subscribersDao.delete(subscriber.value!!)
        }
        _navigateToHome.value = true
    }

    fun pay(){
        val newPayment = Payment(
            subscriber_id = subscriber.value!!.subscriber_id,
            name = subscriber.value!!.name,
            subDate = subscriber.value!!.subDate,
            subPrice = subscriber.value!!.subPrice)
        viewModelScope.launch {
            paymentDao.insert(newPayment)
        }
        _navigateToHome.value = true
    }

    fun history() : LiveData<SubscribersWithPayments?> {
        viewModelScope.launch(Dispatchers.IO) {
            _history.postValue(subscribersDao.getSubscribersWithPaymentsById(subscriber.value?.subscriber_id!!))

        }
        return history
    }

    fun onNavigateToHome(){
        _navigateToHome.value = false
    }



}