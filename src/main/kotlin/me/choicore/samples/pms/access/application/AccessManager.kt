package me.choicore.samples.pms.access.application

import me.choicore.samples.pms.AccessDirection
import me.choicore.samples.pms.LicensePlateNumber
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
        getTicket(complexId, parkingLotId, token)
    }

    private fun exit(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
        exitedAt: LocalDateTime,
    ) {
        getTicket(complexId, parkingLotId, token)
    }

    fun unknown(
        complexId: Long,
        parkingLotId: Long,
        licensePlateNumber: LicensePlateNumber,
        enteredAt: LocalDateTime? = null,
        exitedAt: LocalDateTime? = null,
    ) {
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
