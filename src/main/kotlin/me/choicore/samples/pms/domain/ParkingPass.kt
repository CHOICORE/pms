package me.choicore.samples.pms.domain

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class ParkingPass(
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
    val kind: PassKind = PassKind.PARKING_PASS

    enum class Type {
        RESERVED_VISITOR,
        ON_SITE_VISITOR,
        UNKNOWN,
    }
}
