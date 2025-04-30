package me.choicore.samples.core.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SpringEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher,
) : EventPublisher {
    override fun publish(event: Event) {
        applicationEventPublisher.publishEvent(event)
    }
}
