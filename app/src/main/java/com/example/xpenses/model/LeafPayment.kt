package com.xpenses.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "payments_table")
data class LeafPayment(
    @PrimaryKey(autoGenerate = true)
    var paymentId:Long = 0,
    @ColumnInfo(name = "cost")
    var cost:Double,
    @ColumnInfo(name = "type")
    var type: Int,
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo(name = "dateTime")
    var dateTime: Date = Date()
);