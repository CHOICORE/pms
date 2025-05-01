package me.choicore.samples.pms.domain

data class Vehicle(
    val owner: Owner = Owner.UNKNOWN,
    val licensePlate: LicensePlate,
) {
    constructor(licensePlateNumber: LicensePlateNumber) : this(
        licensePlate = LicensePlate(number = licensePlateNumber),
    )
}
