package me.choicore.samples.pms.application

import me.choicore.samples.core.event.EventPublisher
import me.choicore.samples.pms.domain.Access
import me.choicore.samples.pms.domain.AccessRegisteredEvent
import me.choicore.samples.pms.domain.AccessRepository
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
            val registered: Access = accessRepository.save(access = access)
            log.debug("Publishing event for registered access: id={}", registered.id)
            eventPublisher.publish(event = AccessRegisteredEvent(source = registered))
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
