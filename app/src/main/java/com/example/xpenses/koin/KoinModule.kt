package com.example.xpenses.koin

import com.example.xpenses.Repository
import com.example.xpenses.RepositoryInterface
import com.example.xpenses.room.PaymentsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    // single instance of HelloRepository
   // single<DataBas> { HelloRepositoryImpl() }
    single<RepositoryInterface> { Repository(PaymentsDatabase.getDatabase(androidApplication()).paymentDao,PaymentsDatabase.getDatabase(androidApplication()).dayBudgetDao) }
}