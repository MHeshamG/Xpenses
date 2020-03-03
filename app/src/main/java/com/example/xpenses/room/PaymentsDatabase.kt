package com.example.xpenses.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.xpenses.model.Payment

@Database(entities = [Payment::class], version = 1)
@TypeConverters(DateConverters::class)
    abstract class PaymentsDatabase : RoomDatabase() {

        abstract val paymentDao: PaymentDao

        companion object {

            @Volatile
            private var INSTANCE: PaymentsDatabase? = null

            fun getDatabase(context: Context): PaymentsDatabase {
                if (INSTANCE == null) {
                    synchronized(PaymentsDatabase::class) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            PaymentsDatabase::class.java, "payments.db"
                        ).fallbackToDestructiveMigration()
                            .build()
                    }
                }
                return INSTANCE!!
            }
        }
}