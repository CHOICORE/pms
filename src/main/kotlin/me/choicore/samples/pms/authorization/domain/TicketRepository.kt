package me.choicore.samples.pms.authorization.domain

interface TicketRepository {
    fun findByComplexIdAndParkingLotIdAndToken(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Ticket?
}
