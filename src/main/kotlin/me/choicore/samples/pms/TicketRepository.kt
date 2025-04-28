package me.choicore.samples.pms

interface TicketRepository {
    fun findByComplexIdAndParkingLotIdAndToken(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Ticket?
}
