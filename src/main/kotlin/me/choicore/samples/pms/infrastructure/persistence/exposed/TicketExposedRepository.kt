package me.choicore.samples.pms.infrastructure.persistence.exposed

import me.choicore.samples.pms.domain.Ticket
import me.choicore.samples.pms.domain.TicketRepository
import me.choicore.samples.pms.domain.Token
import org.springframework.stereotype.Repository

@Repository
class TicketExposedRepository : TicketRepository {
    override fun findByComplexIdAndParkingLotIdAndToken(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Ticket? {
        TODO()
    }

    override fun save(ticket: Ticket): Ticket {
        TODO()
    }
}
