package me.choicore.samples.pms.infrastructure.persistence.exposed

import me.choicore.samples.pms.domain.LicensePlateNumber
import me.choicore.samples.pms.domain.ParkingPermit
import me.choicore.samples.pms.domain.ParkingPermitRepository
import me.choicore.samples.pms.infrastructure.persistence.exposed.table.ParkingPermitTable
import org.jetbrains.exposed.sql.insertAndGetId
import org.springframework.stereotype.Repository

@Repository
class ParkingPermitExposedRepository : ParkingPermitRepository {
    override fun save(parkingPermit: ParkingPermit): ParkingPermit {
        val registered: Long =
            ParkingPermitTable
                .insertAndGetId {
                    it[complexId] = parkingPermit.complexId
                    it[parkingLotId] = parkingPermit.parkingLotId
                    it[type] = parkingPermit.type
                    it[lpn] = parkingPermit.vehicle.licensePlate.number.value
                    it[reversedLpn] =
                        parkingPermit.vehicle.licensePlate.number.value
                            .reversed()
                    it[dong] = parkingPermit.destination.dong.value
                    it[ho] = parkingPermit.destination.ho.value
                    it[remarks] = parkingPermit.remarks
                    it[validFrom] = parkingPermit.validFrom
                    it[validTo] = parkingPermit.validTo
                    it[registeredAt] = parkingPermit.registeredAt
                }.value

        return parkingPermit.copy(id = registered)
    }

    override fun findByComplexIdAndParkingLotIdAndLicensePlateNumber(
        complexId: Long,
        parkingLotId: Long,
        licensePlateNumber: LicensePlateNumber,
    ): ParkingPermit? {
        TODO()
    }
}
