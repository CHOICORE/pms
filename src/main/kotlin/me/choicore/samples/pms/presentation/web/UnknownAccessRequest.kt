package me.choicore.samples.pms.presentation.web

import me.choicore.samples.pms.domain.LicensePlateNumber
import java.time.LocalDateTime

data class UnknownAccessRequest(
    val lpn: LicensePlateNumber,
    val enteredAt: LocalDateTime?,
    val exitedAt: LocalDateTime?,
)
