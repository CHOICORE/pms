package me.choicore.samples.pms.authorization.infrastructure.ephemeral.inmemory

import me.choicore.samples.pms.AccessDecision
import me.choicore.samples.pms.LicensePlate
import me.choicore.samples.pms.LicensePlateNumber
import me.choicore.samples.pms.Vehicle
import me.choicore.samples.pms.authorization.domain.Ticket
import me.choicore.samples.pms.authorization.domain.TicketRepository
import me.choicore.samples.pms.authorization.domain.TicketStatus
import me.choicore.samples.pms.authorization.domain.Token
import org.springframework.stereotype.Repository

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
            null,
//                Destination(
//                    dong = Dong("101"),
//                    ho = Ho("101"),
//                ),
            vehicle =
                Vehicle(
                    licensePlate = LicensePlate(number = LicensePlateNumber(value = "123가4567")),
                ),
            decision = AccessDecision.ALLOWED,
            status = TicketStatus.ISSUED,
        )

    override fun save(ticket: Ticket): Ticket = ticket
}
