package me.choicore.samples.pms.presentation.web

import me.choicore.samples.pms.domain.AccessDirection
import me.choicore.samples.pms.domain.Token
import java.time.LocalDateTime

data class AccessRequest(
    val token: Token,
    val timestamp: LocalDateTime,
    val direction: AccessDirection,
)
