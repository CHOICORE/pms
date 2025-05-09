package me.choicore.samples.pms.domain

data class ParkingLot(
    val id: Long,
    val complexId: Long,
    val name: String,
    val totalSpaces: Int,
)
