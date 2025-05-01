package me.choicore.samples.pms.application

import me.choicore.samples.pms.domain.Ticket
import me.choicore.samples.pms.domain.TicketFinder
import me.choicore.samples.pms.domain.Token
import org.springframework.stereotype.Service

@Service
class TicketManager(
    private val ticketFinder: TicketFinder,
) {
    fun getTicket(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Ticket = ticketFinder.find(complexId, parkingLotId, token) ?: throw NoSuchElementException("통행권을 찾을 수 없습니다.")
}
