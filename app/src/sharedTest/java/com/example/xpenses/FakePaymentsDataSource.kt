package com.example.xpenses

import com.example.xpenses.model.Payment
import com.xpenses.model.PaymentType
import java.util.*

class FakePaymentsDataSource {
    companion object {
        fun createPaymentWithCustomDate(date: Date): Payment {
            return Payment(
                cost = 102.0,
                description = "description",
                type = PaymentType.ENTERTAINMENT.typeInt,
                dateTime = date
            )
        }

        fun createPayment(): Payment {
            return Payment(
                cost = 102.0,
                description = "description",
                type = PaymentType.ENTERTAINMENT.typeInt
            )
        }

        fun createPayments() = listOf(
            Payment(
                cost = 102.0,
                description = "description",
                type = PaymentType.FOOD.typeInt
            ),
            Payment(
                cost = 200.0,
                description = "description",
                type = PaymentType.ENTERTAINMENT.typeInt
            ),
            Payment(
                cost = 300.0,
                description = "description",
                type = PaymentType.SHOPPING.typeInt
            )
        )
    }
}