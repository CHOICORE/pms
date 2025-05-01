package me.choicore.samples.pms.domain

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class Ticket(
    val id: Long = 0,
    val complexId: Long,
    val parkingLotId: Long,
    val token: Token,
    val destination: Destination?,
    val vehicle: Vehicle,
    val decision: AccessDecision,
    val status: TicketStatus,
    val issuedAt: LocalDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
) {
    val usable: Boolean = this.destination != null && this.decision == AccessDecision.ALLOWED
}
