package me.choicore.samples.core.event

import java.time.LocalDateTime

abstract class AbstractEvent(
    override val source: Any,
) : Event {
    override val publishedAt: LocalDateTime = LocalDateTime.now()
}
