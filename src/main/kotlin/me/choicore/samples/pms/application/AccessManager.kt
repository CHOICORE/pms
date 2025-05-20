package me.choicore.samples.pms.application

import me.choicore.samples.pms.domain.Access
import me.choicore.samples.pms.domain.AccessDirection
import me.choicore.samples.pms.domain.AccessDirection.IN
import me.choicore.samples.pms.domain.AccessDirection.OUT
import me.choicore.samples.pms.domain.LicensePlateNumber
import me.choicore.samples.pms.domain.Pass
import me.choicore.samples.pms.domain.Token
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AccessManager(
    private val passManager: PassManager,
    private val accessRegistrar: AccessRegistrar,
) {
    fun access(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
        timestamp: LocalDateTime,
        direction: AccessDirection,
    ) {
        log.info(
            "Processing {} access for token {} at complex {} and parking lot {}",
            direction,
            token.value,
            complexId,
            parkingLotId,
        )

        when (direction) {
            IN -> enter(complexId = complexId, parkingLotId = parkingLotId, token = token, enteredAt = timestamp)
            OUT -> exit(complexId = complexId, parkingLotId = parkingLotId, token = token, exitedAt = timestamp)
        }

        log.debug("Access {} processed successfully for token {}", direction, token.value)
    }

    fun unknown(
        complexId: Long,
        parkingLotId: Long,
        licensePlateNumber: LicensePlateNumber,
        enteredAt: LocalDateTime? = null,
        exitedAt: LocalDateTime? = null,
    ) {
        log.info(
            "Processing unknown vehicle access for license plate {} at complex {} and parking lot {}",
            licensePlateNumber.value,
            complexId,
            parkingLotId,
        )

        val accessDirections =
            listOfNotNull(
                enteredAt?.let {
                    log.debug("Unknown vehicle entered at {}", it)
                    IN to it
                },
                exitedAt?.let {
                    log.debug("Unknown vehicle exited at {}", it)
                    OUT to it
                },
            )

        if (accessDirections.isEmpty()) {
            log.warn("No entry or exit timestamps provided for unknown vehicle {}", licensePlateNumber.value)
            return
        }

        accessDirections.forEach { (direction, accessedAt) ->
            log.debug(
                "Registering {} access for unknown vehicle {} at {}",
                direction,
                licensePlateNumber.value,
                accessedAt,
            )
            accessRegistrar.register(
                Access.unknown(
                    complexId = complexId,
                    parkingLotId = parkingLotId,
                    licensePlateNumber = licensePlateNumber,
                    direction = direction,
                    accessedAt = accessedAt,
                ),
            )
        }

        log.info(
            "Successfully registered {} access events for unknown vehicle {}",
            accessDirections.size,
            licensePlateNumber.value,
        )
    }

    private fun enter(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
        enteredAt: LocalDateTime,
    ) {
        log.debug("Processing entry for token {} at {}", token.value, enteredAt)
        try {
            val pass: Pass = getPass(complexId, parkingLotId, token)
            val entry = pass.markAsEntered(at = enteredAt)
            accessRegistrar.register(access = entry)

            log.info("Vehicle successfully entered with token {} at {}", token.value, enteredAt)
        } catch (e: Exception) {
            log.error("Failed to process entry for token {}: {}", token.value, e.message, e)
            throw e
        }
    }

    private fun exit(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
        exitedAt: LocalDateTime,
    ) {
        log.debug("Processing exit for token {} at {}", token.value, exitedAt)
        try {
            val pass: Pass = getPass(complexId, parkingLotId, token)
            val exit = pass.markAsExited(at = exitedAt)
            accessRegistrar.register(access = exit)

            log.info("Vehicle successfully exited with token {} at {}", token.value, exitedAt)
        } catch (e: Exception) {
            log.error("Failed to process exit for token {}: {}", token.value, e.message, e)
            throw e
        }
    }

    private fun getPass(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Pass {
        log.debug(
            "Retrieving pass for token {} at complex {} and parking lot {}",
            token.value,
            complexId,
            parkingLotId,
        )
        return passManager
            .getPass(complexId = complexId, parkingLotId = parkingLotId, token = token)
            .also { log.debug("Found pass for token {}: {}", token.value, it.id) }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AccessManager::class.java)
    }
}
