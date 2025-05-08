package me.choicore.samples.pms.domain

data class Vehicle(
    val driver: Driver = Driver.UNKNOWN,
    val licensePlate: LicensePlate,
) {
    constructor(licensePlateNumber: LicensePlateNumber) : this(
        licensePlate = LicensePlate(number = licensePlateNumber),
    )
}
