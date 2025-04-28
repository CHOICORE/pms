package me.choicore.samples.pms

data class AccessResponse(
    val lpn: String,
    val instructions: Set<Instruction>? = null,
)
