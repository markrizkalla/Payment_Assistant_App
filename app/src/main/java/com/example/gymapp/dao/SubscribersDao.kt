package com.example.gymapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gymapp.model.Subscriber

@Dao
interface SubscribersDao {



    @Insert
    suspend fun insert(subscriber: Subscriber)

    @Update
    suspend fun update(subscriber: Subscriber)

    @Delete
    suspend fun delete(subscriber: Subscriber)


    @Query("SELECT * FROM gym_table WHERE subscriber_id = :subscriber_id")
    fun get(subscriber_id: Int) : LiveData<Subscriber>

    @Query("SELECT * FROM gym_table")
    fun getAll() : LiveData<List<Subscriber>>
}