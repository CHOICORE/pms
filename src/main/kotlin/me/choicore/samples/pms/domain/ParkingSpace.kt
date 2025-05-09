package me.choicore.samples.pms.domain

data class ParkingSpace(
    val id: Long,
    val parkingZoneId: Long,
    val parkingLotId: Long,
    val complexId: Long,
    val name: String,
    val status: Status,
) {
    enum class Status {
        AVAILABLE,
        OCCUPIED,
        RESERVED,
        UNAVAILABLE,
    }
}
