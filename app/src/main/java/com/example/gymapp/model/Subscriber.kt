package com.example.gymapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "gym_table")
data class Subscriber(
    @PrimaryKey(autoGenerate = true)
    var subscriber_id: Int = 0,
    var name:String,
    @ColumnInfo(name = "subscribe_date")
    var subDate:String,
    @ColumnInfo(name = "subscribe_end_date")
    var sebEndDate:String,
    @ColumnInfo(name = "subscribe_price")
    var subPrice: String
)
