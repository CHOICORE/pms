package me.choicore.samples.pms.access.presentation.web

import me.choicore.samples.pms.Instruction

data class AccessResponse(
    val lpn: String,
    val instructions: Set<Instruction>? = null,
)
