package me.choicore.samples.pms.domain

interface PermitRepository {
    fun save(permit: Permit): Permit

    fun findByComplexIdAndParkingLotIdAndLicensePlateNumber(
        complexId: Long,
        parkingLotId: Long,
        licensePlateNumber: LicensePlateNumber,
    ): Permit?
}
