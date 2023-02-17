package com.example.gymapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gymapp.model.Payment
import com.example.gymapp.model.Subscriber

@Dao
interface PaymentDao {

    @Insert
    suspend fun insert(payment: Payment)

    @Update
    suspend fun update(payment: Payment)

    @Delete
    suspend fun delete(payment: Payment)

    @Query("SELECT * FROM payment_table WHERE subscriber_id = :subscriber_id")
    fun get(subscriber_id: Int) : Payment

    @Query("SELECT * FROM payment_table")
    fun getAll() : LiveData<List<Payment>>
}