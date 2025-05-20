package me.choicore.samples.pms.domain

import me.choicore.samples.pms.domain.Access.Entry
import me.choicore.samples.pms.domain.Access.Exit
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class Pass(
    val id: Long = 0,
    val complexId: Long,
    val parkingLotId: Long,
    val ref: Long? = null,
    val kind: PassKind,
    val token: Token,
    val destination: Destination?,
    val vehicle: Vehicle,
    val decision: AccessDecision,
    var status: PassStatus,
    val issuedBy: String,
    val issuedAt: LocalDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
) {
    fun markAsEntered(at: LocalDateTime): Entry {
        check(this.decision == AccessDecision.ALLOWED) {
            "통행이 불가능한 통행권입니다. (id=${this.token}, decision=${this.decision})"
        }

        check(this.destination != null) {
            "통행권에 목적지 정보가 누락되어 있습니다. (id=${this.token}, destination=${this.destination})"
        }

        this.status = PassStatus.ENTERED

        return Entry(
            complexId = this.complexId,
            parkingLotId = this.parkingLotId,
            vehicle = this.vehicle,
            token = this.token,
            destination = this.destination,
            enteredAt = at,
        )
    }

    fun markAsExited(at: LocalDateTime): Exit {
        this.status = PassStatus.EXITED

        return Exit(
            complexId = this.complexId,
            parkingLotId = this.parkingLotId,
            vehicle = this.vehicle,
            token = this.token,
            exitedAt = at,
        )
    }
}
