package me.choicore.samples.pms

data class Vehicle(
    val owner: Owner = Owner.UNKNOWN,
    val licensePlate: LicensePlate,
)
