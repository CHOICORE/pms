package me.choicore.samples.pms

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class Ticket(
    val id: Long = 0,
    val complexId: Long,
    val parkingLotId: Long,
    val code: Token,
    val destination: Destination?,
    val vehicle: Vehicle,
    val decision: AccessDecision,
    val status: TicketStatus,
    val issuedAt: LocalDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
) {
    val expired: Boolean = this.status == TicketStatus.EXPIRED
    val usable: Boolean =
        (!this.expired && this.decision == AccessDecision.ALLOWED) && (status == TicketStatus.ISSUED || status == TicketStatus.RENEWED)
}
