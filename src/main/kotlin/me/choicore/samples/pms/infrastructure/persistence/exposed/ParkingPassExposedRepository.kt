package me.choicore.samples.pms.infrastructure.persistence.exposed

import me.choicore.samples.pms.domain.LicensePlateNumber
import me.choicore.samples.pms.domain.ParkingPass
import me.choicore.samples.pms.domain.ParkingPassRepository
import org.springframework.stereotype.Repository

@Repository
class ParkingPassExposedRepository : ParkingPassRepository {
    override fun save(parkingPass: ParkingPass): ParkingPass {
        TODO()
    }

    override fun findByComplexIdAndParkingLotIdAndLicensePlateNumber(
        complexId: Long,
        parkingLotId: Long,
        licensePlateNumber: LicensePlateNumber,
    ): ParkingPass? {
        TODO()
    }
}
