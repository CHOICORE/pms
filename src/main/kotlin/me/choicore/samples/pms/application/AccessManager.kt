package me.choicore.samples.pms.application

import me.choicore.samples.pms.domain.Access
import me.choicore.samples.pms.domain.AccessDirection
import me.choicore.samples.pms.domain.AccessDirection.IN
import me.choicore.samples.pms.domain.AccessDirection.OUT
import me.choicore.samples.pms.domain.LicensePlateNumber
import me.choicore.samples.pms.domain.Ticket
import me.choicore.samples.pms.domain.Token
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AccessManager(
    private val ticketManager: TicketManager,
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
            IN -> enter(complexId = complexId, parkingLotId = parkingLotId, token = token, enteredAt = timestamp)
            OUT -> exit(complexId = complexId, parkingLotId = parkingLotId, token = token, exitedAt = timestamp)
        }
    }

    private fun enter(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
        enteredAt: LocalDateTime,
    ) {
        val ticket: Ticket = ticketManager.getTicket(complexId = complexId, parkingLotId = parkingLotId, token = token)
        val entry = ticket.enter(at = enteredAt)
        accessRegistrar.register(access = entry)
    }

    private fun exit(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
        exitedAt: LocalDateTime,
    ) {
        val ticket: Ticket = ticketManager.getTicket(complexId = complexId, parkingLotId = parkingLotId, token = token)
        val exit = ticket.exit(at = exitedAt)
        accessRegistrar.register(access = exit)
    }

    fun unknown(
        complexId: Long,
        parkingLotId: Long,
        licensePlateNumber: LicensePlateNumber,
        enteredAt: LocalDateTime? = null,
        exitedAt: LocalDateTime? = null,
    ) {
        listOfNotNull(
            enteredAt?.let {
                IN to it
            },
            exitedAt?.let {
                OUT to it
            },
        ).forEach { (direction, accessedAt) ->
            accessRegistrar.register(
                Access.unknown(
                    complexId = complexId,
                    parkingLotId = parkingLotId,
                    licensePlateNumber = licensePlateNumber,
                    direction = direction,
                    accessedAt = accessedAt,
                ),
            )
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AccessManager::class.java)
    }
}
