//package com.example.xpenses
//
//import androidx.room.Room
//import androidx.test.platform.app.InstrumentationRegistry
//import com.xwallet.room.PaymentDao
//import com.xwallet.room.PaymentsDatabase
//import org.junit.Before
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJU2nit4::class)
//class DatabaseTest {
//    private lateinit var paymentDao: PaymentDao
//    private lateinit var db:PaymentsDatabase
//
//    @Before
//    fun createDb(){
//        val context = InstrumentationRegistry.getInstrumentation().targetContext
//        // Using an in-memory database because the information stored here disappears when the
//        // process is killed.
//        db = Room.inMemoryDatabaseBuilder(context, PaymentsDatabase::class.java)
//            // Allowing main thread queries, just for testing.
//            .allowMainThreadQueries()
//            .build()
//        paymentDao = PaymentsDatabase
//    }
//}