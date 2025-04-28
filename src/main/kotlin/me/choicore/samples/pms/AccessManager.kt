package me.choicore.samples.pms

import me.choicore.samples.pms.AccessDirection.IN
import me.choicore.samples.pms.AccessDirection.OUT
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AccessManager {
    fun access(
        complexId: Long,
        parkingLotId: Long,
        code: Token,
        timestamp: LocalDateTime,
        direction: AccessDirection,
    ) {
        when (direction) {
            IN -> enter(complexId = complexId, parkingLotId = parkingLotId, code = code, enteredAt = timestamp)
            OUT -> exit(complexId = complexId, parkingLotId = parkingLotId, code = code, exitedAt = timestamp)
        }
    }

    private fun enter(
        complexId: Long,
        parkingLotId: Long,
        code: Token,
        enteredAt: LocalDateTime,
    ) {
    }

    private fun exit(
        complexId: Long,
        parkingLotId: Long,
        code: Token,
        exitedAt: LocalDateTime,
    ) {
    }

    fun unknown(
        complexId: Long,
        parkingLotId: Long,
        licensePlateNumber: LicensePlateNumber,
        enteredAt: LocalDateTime? = null,
        exitedAt: LocalDateTime? = null,
    ) {
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AccessManager::class.java)
    }
}
