package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.xpenses.room.PaymentDao

class TodayPaymentsFragmentViewModel(val paymentDao: PaymentDao, application: Application):AndroidViewModel(application) {
    val todayPayments = paymentDao.getAllPayments()
}