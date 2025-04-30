package me.choicore.samples.pms.access.application

import me.choicore.samples.core.event.EventPublisher
import me.choicore.samples.pms.access.domain.Access
import me.choicore.samples.pms.access.domain.AccessRegisteredEvent
import me.choicore.samples.pms.access.domain.AccessRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AccessRegistrar(
    private val accessRepository: AccessRepository,
    private val eventPublisher: EventPublisher,
) {
    @Transactional
    fun register(access: Access): Long {
        log.info("Starting registration process for access: type={}", access::class.simpleName)

        try {
            val registered: Access =
                when (access) {
                    is Access.Entry -> {
                        log.debug("Registering entry access: token={}, enteredAt={}", access.token, access.enteredAt)
                        accessRepository.save(entry = access)
                    }

                    is Access.Exit -> {
                        log.debug("Registering exit access: token={}, exitedAt={}", access.token, access.exitedAt)
                        accessRepository.save(exit = access)
                    }

                    is Access.Unknown -> {
                        log.debug(
                            "Registering unknown access: direction={}, accessedAt={}",
                            access.direction,
                            access.accessedAt,
                        )
                        accessRepository.save(unknown = access)
                    }
                }

            val event = AccessRegisteredEvent(source = registered)
            log.debug("Publishing event for registered access: id={}", registered.id)
            eventPublisher.publish(event = event)

            return registered.id
        } catch (e: Exception) {
            log.error("Failed to register access: type={}, error={}", access::class.simpleName, e.message, e)
            throw e
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AccessRegistrar::class.java)
    }
}
