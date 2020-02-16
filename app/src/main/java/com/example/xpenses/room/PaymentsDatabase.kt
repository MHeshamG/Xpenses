package com.xpenses.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xpenses.model.LeafPayment

@Database(entities = [LeafPayment::class], version = 1)
    abstract class PaymentsDatabase : RoomDatabase() {

        abstract val paymentDao:PaymentDao

        companion object {

            @Volatile
            private var INSTANCE: PaymentsDatabase? = null

            fun getDatabase(context: Context): PaymentsDatabase{
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