package me.choicore.samples.pms.application

import me.choicore.samples.core.event.EventPublisher
import me.choicore.samples.pms.domain.AccessRegisteredEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class AccessEventListener(
    private val eventPublisher: EventPublisher,
) {
    @TransactionalEventListener(classes = [AccessRegisteredEvent::class], phase = TransactionPhase.AFTER_COMMIT)
    fun onAfterCommitAccessRegisteredEvent(event: AccessRegisteredEvent) {
        log.info("[Phase: {}], Access registered event received: {}", TransactionPhase.AFTER_COMMIT, event)
    }

    @TransactionalEventListener(classes = [AccessRegisteredEvent::class], phase = TransactionPhase.BEFORE_COMMIT)
    fun onBeforeCommitAccessRegisteredEvent(event: AccessRegisteredEvent) {
        log.info("[Phase: {}] Access registered event received: {}", TransactionPhase.BEFORE_COMMIT, event)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AccessEventListener::class.java)
    }
}
