package com.example.xpenses

import com.xpenses.model.LeafPayment
import com.xpenses.model.PaymentType
import java.util.*

class FakePaymentsDataSource {
    companion object {
        fun createPaymentWithCustomDate(date: Date): LeafPayment {
            return LeafPayment(
                cost = 102.0,
                description = "description",
                type = PaymentType.ENTERTAINMENT.typeInt,
                dateTime = date
            )
        }

        fun createPayment(): LeafPayment {
            return LeafPayment(
                cost = 102.0,
                description = "description",
                type = PaymentType.ENTERTAINMENT.typeInt
            )
        }
    }
}