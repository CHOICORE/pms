package me.choicore.samples.pms.access.domain

import me.choicore.samples.pms.Destination
import me.choicore.samples.pms.Vehicle
import me.choicore.samples.pms.authorization.domain.Token
import java.time.LocalDateTime

data class Exit(
    val complexId: Long,
    val parkingLotId: Long,
    val token: Token,
    val vehicle: Vehicle,
    val destination: Destination,
    val enteredAt: LocalDateTime,
    val registeredBy: String,
) {
    data class Unknown(
        val complexId: Long,
        val parkingLotId: Long,
        val vehicle: Vehicle,
        val enteredAt: LocalDateTime,
    )
}
