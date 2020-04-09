package com.example.xpenses.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.xpenses.model.DayBudget
import com.example.xpenses.model.Payment
import java.util.*

@Dao
interface DayBudgetDao {

    @Insert
    fun addDayBudget(dayBudget: DayBudget)

    @Update
    fun updateDayBudget(dayBudget: DayBudget)

    @Delete
    fun deleteDayBudget(dayBudget: DayBudget)

    @Query("SELECT * FROM day_budget_table WHERE date=:dayDate")
    fun fetchDayBudgetByDate(dayDate: Date): LiveData<DayBudget>

}