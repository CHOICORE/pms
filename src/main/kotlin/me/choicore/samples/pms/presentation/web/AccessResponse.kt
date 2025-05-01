package me.choicore.samples.pms.presentation.web

import me.choicore.samples.pms.domain.Instruction

data class AccessResponse(
    val lpn: String,
    val instructions: Set<Instruction>? = null,
)
