package me.choicore.samples.pms.domain

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class ParkingPermit(
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
    val kind: PassKind = PassKind.PARKING_PERMIT

    enum class Type {
        RESIDENT,
        TENANT,
        EMPLOYEE,
        OTHER,
    }
}
