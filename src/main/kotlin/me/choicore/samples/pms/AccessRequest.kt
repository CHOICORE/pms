package me.choicore.samples.pms

import java.time.LocalDateTime

data class AccessRequest(
    val code: Token,
    val timestamp: LocalDateTime,
    val direction: AccessDirection,
)
