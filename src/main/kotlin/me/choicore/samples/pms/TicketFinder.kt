package me.choicore.samples.pms

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TicketFinder(
    private val ticketRepository: TicketRepository,
) {
    fun find(
        complexId: Long,
        parkingLotId: Long,
        code: Token,
    ): Ticket? {
        val found: Ticket? =
            ticketRepository
                .findByComplexIdAndParkingLotIdAndToken(
                    complexId = complexId,
                    parkingLotId = parkingLotId,
                    token = code,
                )

        return found
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(TicketFinder::class.java)
    }
}
