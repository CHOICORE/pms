package me.choicore.samples.pms.access.domain

import me.choicore.samples.pms.AccessDirection
import me.choicore.samples.pms.Destination
import me.choicore.samples.pms.Vehicle
import me.choicore.samples.pms.authorization.domain.Token
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

sealed class Access {
    abstract val id: Long
    abstract val complexId: Long
    abstract val parkingLotId: Long
    abstract val vehicle: Vehicle

    data class Entry(
        override val id: Long = 0,
        override val complexId: Long,
        override val parkingLotId: Long,
        override val vehicle: Vehicle,
        val token: Token,
        val destination: Destination,
        val enteredAt: LocalDateTime,
    ) : Access()

    data class Exit(
        override val id: Long = 0,
        override val complexId: Long,
        override val parkingLotId: Long,
        override val vehicle: Vehicle,
        val token: Token,
        val exitedAt: LocalDateTime,
    ) : Access()

    data class Unknown(
        override val id: Long = 0,
        override val complexId: Long,
        override val parkingLotId: Long,
        override val vehicle: Vehicle,
        val direction: AccessDirection,
        val accessedAt: LocalDateTime,
    ) : Access()

    fun register(accessRepository: AccessRepository): AccessRegisteredEvent {
        val registered: Access =
            when (this) {
                is Entry -> {
                    log.debug("Registering entry access: token={}, enteredAt={}", token, enteredAt)
                    accessRepository.save(entry = this)
                }

                is Exit -> {
                    log.debug("Registering exit access: token={}, exitedAt={}", token, exitedAt)
                    accessRepository.save(exit = this)
                }

                is Unknown -> {
                    log.debug("Registering unknown access: direction={}, accessedAt={}", direction, accessedAt)
                    accessRepository.save(unknown = this)
                }
            }

        return AccessRegisteredEvent(source = registered)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(Access::class.java)
    }
}
