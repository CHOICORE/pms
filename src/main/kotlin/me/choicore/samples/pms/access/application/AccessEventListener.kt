package me.choicore.samples.pms.access.application

import me.choicore.samples.pms.access.domain.AccessRegisteredEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class AccessEventListener {
    @TransactionalEventListener(classes = [AccessRegisteredEvent::class])
    fun onAccessRegisteredEvent(event: AccessRegisteredEvent) {
        log.info("Access registered event received: {}", event)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AccessEventListener::class.java)
    }
}
