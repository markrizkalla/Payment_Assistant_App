package com.example.gymapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymapp.dao.PaymentDao
import com.example.gymapp.model.Payment
import com.example.gymapp.model.Subscriber

@Database(entities = [Payment::class,Subscriber::class], version = 2, exportSchema = false)
abstract class PaymentsDatabase :RoomDatabase() {

    abstract val paymentDao:PaymentDao

    companion object{
        @Volatile
        private var INSTANCE: PaymentsDatabase? = null

        fun getInstance(context: Context):PaymentsDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PaymentsDatabase::class.java,
                        "payment_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}