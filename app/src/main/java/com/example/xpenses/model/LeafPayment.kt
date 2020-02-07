package com.xwallet.business

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments_table")
data class LeafPayment(
    @PrimaryKey(autoGenerate = true)
    var paymentId:Long=1L,
    @ColumnInfo(name = "cost")
    var cost:Double,
    @ColumnInfo(name = "type")
    var type: Int,
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo(name = "dateTime")
    var dateTime: Long = System.currentTimeMillis()
);