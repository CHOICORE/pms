package me.choicore.samples.pms

import org.springframework.stereotype.Repository

@Repository
class TicketInMemoryRepository : TicketRepository {
    override fun findByComplexIdAndParkingLotIdAndToken(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Ticket? =
        Ticket(
            code = token,
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
}
