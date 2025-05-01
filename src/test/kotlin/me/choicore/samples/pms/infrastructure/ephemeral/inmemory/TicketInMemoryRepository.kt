package me.choicore.samples.pms.infrastructure.ephemeral.inmemory

import me.choicore.samples.pms.domain.AccessDecision
import me.choicore.samples.pms.domain.Destination
import me.choicore.samples.pms.domain.Dong
import me.choicore.samples.pms.domain.Ho
import me.choicore.samples.pms.domain.LicensePlate
import me.choicore.samples.pms.domain.LicensePlateNumber
import me.choicore.samples.pms.domain.Ticket
import me.choicore.samples.pms.domain.TicketRepository
import me.choicore.samples.pms.domain.TicketStatus
import me.choicore.samples.pms.domain.Token
import me.choicore.samples.pms.domain.Vehicle
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository

@Primary
@Repository
class TicketInMemoryRepository : TicketRepository {
    override fun findByComplexIdAndParkingLotIdAndToken(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Ticket? =
        Ticket(
            token = token,
            complexId = complexId,
            parkingLotId = parkingLotId,
            destination =
                Destination(
                    dong = Dong("101"),
                    ho = Ho("101"),
                ),
            vehicle =
                Vehicle(
                    licensePlate = LicensePlate(number = LicensePlateNumber(value = "123가4567")),
                ),
            decision = AccessDecision.ALLOWED,
            status = TicketStatus.ISSUED,
        )

    override fun save(ticket: Ticket): Ticket = ticket
}
