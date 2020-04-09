package com.example.xpenses.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.xpenses.DateTimeProvider.Companion.getTodayDate
import java.util.*

@Entity(tableName = "day_budget_table")
class DayBudget(
    @ColumnInfo(name = "budget")
    var budget:Double,
    @PrimaryKey
    @ColumnInfo(name = "date")
    var dayDate: Date = getTodayDate()
)