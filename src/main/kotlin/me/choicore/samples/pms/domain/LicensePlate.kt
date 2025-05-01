package me.choicore.samples.pms.domain

data class LicensePlate(
    val number: LicensePlateNumber,
    val image: String? = null,
)
