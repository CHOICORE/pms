package me.choicore.samples.core.event

import java.time.LocalDateTime

interface Event {
    val source: Any
    val publishedAt: LocalDateTime
}
