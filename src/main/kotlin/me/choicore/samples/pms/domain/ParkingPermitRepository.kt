package me.choicore.samples.pms.domain

interface ParkingPermitRepository {
    fun save(parkingPermit: ParkingPermit): ParkingPermit

    fun findByComplexIdAndParkingLotIdAndLicensePlateNumber(
        complexId: Long,
        parkingLotId: Long,
        licensePlateNumber: LicensePlateNumber,
    ): ParkingPermit?
}
