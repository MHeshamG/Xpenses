package com.xpenses.model

enum class PaymentType(
    val typeInt:Int
) {
    FOOD(0),
    ENTERTAINMENT(1),
    FUEL(2),
    TRANSPORTATION(3),
    MAINTANINCE(4),
    SHOPPING(5),
    SCHOOL(6),
    CLOTHES(7),
    TRAVEL(8);

    companion object {
        private val map = values().associateBy { paymentType: PaymentType -> paymentType.typeInt }
        fun fromInt(type: Int) = map[type]
    }

}