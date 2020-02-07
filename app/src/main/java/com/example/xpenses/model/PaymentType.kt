package com.xwallet.business

enum class PaymentType(
    val typeInt:Int
) {
    FOOD(0),
    ENTERTAINMENT(1),
    FUEL(2),
    TRANSPORTATION(3),
    HOME_RENT(4),
    OTHER(5);

    companion object {
        private val map = values().associateBy { paymentType: PaymentType -> paymentType.typeInt }
        fun fromInt(type: Int) = map[type]
    }

}