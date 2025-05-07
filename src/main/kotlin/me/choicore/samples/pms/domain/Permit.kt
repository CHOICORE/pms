package me.choicore.samples.pms.domain

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class Permit(
    val id: Long = 0,
    val complexId: Long,
    val parkingLotId: Long,
    val destination: Destination,
    val vehicle: Vehicle,
    val type: Type,
    val remarks: String? = null,
    val validFrom: LocalDateTime? = null,
    val validTo: LocalDateTime? = null,
    val registeredBy: String,
    val registeredAt: LocalDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
) {
    enum class Type {
        RESIDENT,
        TENANT,
        VISITOR,
        EMPLOYEE,
        GUEST,
    }
}
