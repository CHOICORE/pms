package me.choicore.samples.pms.domain

interface ParkingPassRepository {
    fun save(parkingPass: ParkingPass): ParkingPass

    fun findByComplexIdAndParkingLotIdAndLicensePlateNumber(
        complexId: Long,
        parkingLotId: Long,
        licensePlateNumber: LicensePlateNumber,
    ): ParkingPass?
}
