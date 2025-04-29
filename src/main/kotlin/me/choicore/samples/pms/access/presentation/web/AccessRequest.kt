package me.choicore.samples.pms.access.presentation.web

import me.choicore.samples.pms.AccessDirection
import me.choicore.samples.pms.authorization.domain.Token
import java.time.LocalDateTime

data class AccessRequest(
    val token: Token,
    val timestamp: LocalDateTime,
    val direction: AccessDirection,
)
