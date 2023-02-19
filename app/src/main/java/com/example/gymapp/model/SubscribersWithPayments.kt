package com.example.gymapp.model

import androidx.room.Embedded
import androidx.room.Relation

data class SubscribersWithPayments(
    @Embedded val subscriber: Subscriber,
    @Relation(
        parentColumn = "subscriber_id",
        entityColumn = "subscriber_id"
    )
    val payments:List<Payment>
)
