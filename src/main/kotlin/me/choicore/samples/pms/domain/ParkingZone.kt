package me.choicore.samples.pms.domain

data class ParkingZone(
    val id: Long,
    val parkingLotId: Long,
    val complexId: Long,
    val name: String,
    val floor: Int,
    val type: Type,
) {
    enum class Type {
        REGULAR,
        HANDICAP,
        ELECTRIC,
    }
}
