package me.choicore.samples.pms.domain

import me.choicore.samples.common.util.truncateToSeconds
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

sealed class Access {
    abstract val id: Long
    abstract val complexId: Long
    abstract val parkingLotId: Long
    abstract val vehicle: Vehicle

    data class Entry(
        override val id: Long = 0,
        override val complexId: Long,
        override val parkingLotId: Long,
        override val vehicle: Vehicle,
        val token: Token,
        val destination: Destination,
        val enteredAt: LocalDateTime,
    ) : Access()

    data class Exit(
        override val id: Long = 0,
        override val complexId: Long,
        override val parkingLotId: Long,
        override val vehicle: Vehicle,
        val token: Token,
        val exitedAt: LocalDateTime,
    ) : Access()

    data class Unknown(
        override val id: Long = 0,
        override val complexId: Long,
        override val parkingLotId: Long,
        override val vehicle: Vehicle,
        val direction: AccessDirection,
        val accessedAt: LocalDateTime,
    ) : Access()

    companion object {
        private val log: Logger = LoggerFactory.getLogger(Access::class.java)

        fun unknown(
            complexId: Long,
            parkingLotId: Long,
            licensePlateNumber: LicensePlateNumber,
            direction: AccessDirection,
            accessedAt: LocalDateTime,
        ): Access {
            val vehicle = Vehicle(licensePlate = LicensePlate(licensePlateNumber))
            return Unknown(
                complexId = complexId,
                parkingLotId = parkingLotId,
                vehicle = vehicle,
                direction = direction,
                accessedAt = accessedAt.truncateToSeconds(),
            )
        }
    }
}
