package com.example.xpenses.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.xpenses.room.PaymentDao
import com.xwallet.business.LeafPayment

class TodayPaymentsFragmentViewModel(val paymentDao: PaymentDao, application: Application):AndroidViewModel(application) {
    val todayPayments = paymentDao.getAllPayments()
}