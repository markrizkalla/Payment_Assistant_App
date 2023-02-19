package com.example.gymapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "payment_table")
data class Payment(
    var subscriber_id :Int = 0,
    var name : String,
    @ColumnInfo(name = "subscribe_date")
    var subDate:String,
    @ColumnInfo(name = "subscribe_price")
    var subPrice: String

){
    @PrimaryKey(autoGenerate = true)
    var paymentId:Int = 0

}
