package com.example.xpenses.koin

import org.koin.dsl.module

val appModule = module {

    // single instance of HelloRepository
   // single<DataBas> { HelloRepositoryImpl() }
}