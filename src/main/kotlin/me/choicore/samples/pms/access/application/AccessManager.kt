package me.choicore.samples.pms.access.application

import me.choicore.samples.common.util.truncateToSeconds
import me.choicore.samples.pms.AccessDecision
import me.choicore.samples.pms.AccessDirection
import me.choicore.samples.pms.LicensePlate
import me.choicore.samples.pms.LicensePlateNumber
import me.choicore.samples.pms.Vehicle
import me.choicore.samples.pms.access.domain.Access.Entry
import me.choicore.samples.pms.access.domain.Access.Exit
import me.choicore.samples.pms.access.domain.Access.Unknown
import me.choicore.samples.pms.authorization.domain.Ticket
import me.choicore.samples.pms.authorization.domain.TicketFinder
import me.choicore.samples.pms.authorization.domain.Token
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AccessManager(
    private val ticketFinder: TicketFinder,
    private val accessRegistrar: AccessRegistrar,
) {
    fun access(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
        timestamp: LocalDateTime,
        direction: AccessDirection,
    ) {
        when (direction) {
            AccessDirection.IN ->
                enter(
                    complexId = complexId,
                    parkingLotId = parkingLotId,
                    token = token,
                    enteredAt = timestamp,
                )

            AccessDirection.OUT ->
                exit(
                    complexId = complexId,
                    parkingLotId = parkingLotId,
                    token = token,
                    exitedAt = timestamp,
                )
        }
    }

    private fun enter(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
        enteredAt: LocalDateTime,
    ) {
        val ticket: Ticket = getTicket(complexId, parkingLotId, token)

        check(ticket.decision == AccessDecision.ALLOWED) {
            "통행이 불가능한 통행권입니다. (id=${ticket.token}, decision=${ticket.decision})"
        }

        check(ticket.destination != null) {
            "통행권에 목적지가 설정되어 있지 않습니다. (id=${ticket.token}, destination=${ticket.destination})"
        }

        val entry =
            Entry(
                complexId = ticket.complexId,
                parkingLotId = ticket.parkingLotId,
                token = ticket.token,
                vehicle = ticket.vehicle,
                destination = ticket.destination,
                enteredAt = enteredAt.truncateToSeconds(),
            )

        accessRegistrar.register(entry)
    }

    private fun exit(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
        exitedAt: LocalDateTime,
    ) {
        val ticket = getTicket(complexId, parkingLotId, token)

        val exit =
            Exit(
                complexId = complexId,
                parkingLotId = parkingLotId,
                token = token,
                vehicle = ticket.vehicle,
                exitedAt = exitedAt.truncateToSeconds(),
            )

        accessRegistrar.register(exit)
    }

    fun unknown(
        complexId: Long,
        parkingLotId: Long,
        licensePlateNumber: LicensePlateNumber,
        enteredAt: LocalDateTime? = null,
        exitedAt: LocalDateTime? = null,
    ) {
        val vehicle = Vehicle(licensePlate = LicensePlate(licensePlateNumber))

        if (enteredAt != null) {
            val unknown =
                Unknown(
                    complexId = complexId,
                    parkingLotId = parkingLotId,
                    vehicle = vehicle,
                    direction = AccessDirection.IN,
                    accessedAt = enteredAt,
                )
            accessRegistrar.register(unknown)
        }

        if (exitedAt != null) {
            val unknown =
                Unknown(
                    complexId = complexId,
                    parkingLotId = parkingLotId,
                    vehicle = vehicle,
                    direction = AccessDirection.OUT,
                    accessedAt = exitedAt,
                )
            accessRegistrar.register(unknown)
        }
    }

    private fun getTicket(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Ticket = ticketFinder.find(complexId, parkingLotId, token) ?: throw NoSuchElementException("통행권을 찾을 수 없습니다.")

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AccessManager::class.java)
    }
}
