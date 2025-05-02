package me.choicore.samples.pms.domain

import me.choicore.samples.pms.domain.Access.Entry
import me.choicore.samples.pms.domain.Access.Exit
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
    var status: TicketStatus,
    val issuedAt: LocalDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
) {
    fun enter(at: LocalDateTime): Entry {
        check(this.decision == AccessDecision.ALLOWED) {
            "통행이 불가능한 통행권입니다. (id=${this.token}, decision=${this.decision})"
        }

        check(this.destination != null) {
            "통행권에 목적지 정보가 누락되어 있습니다. (id=${this.token}, destination=${this.destination})"
        }

        this.status = TicketStatus.IN_USE

        return Entry(
            complexId = this.complexId,
            parkingLotId = this.parkingLotId,
            vehicle = this.vehicle,
            token = this.token,
            destination = this.destination,
            enteredAt = at,
        )
    }

    fun exit(at: LocalDateTime): Exit {
        this.status = TicketStatus.USED
        return Exit(
            complexId = this.complexId,
            parkingLotId = this.parkingLotId,
            vehicle = this.vehicle,
            token = this.token,
            exitedAt = at,
        )
    }
}
