package me.choicore.samples.pms.domain

enum class Instruction(
    val description: String,
) {
    REQUIRES_NOTIFICATION(description = "입출차 통지"),
}
