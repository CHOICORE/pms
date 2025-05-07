package me.choicore.samples.pms.infrastructure.persistence.exposed

import me.choicore.samples.pms.domain.LicensePlateNumber
import me.choicore.samples.pms.domain.Permit
import me.choicore.samples.pms.domain.PermitRepository
import me.choicore.samples.pms.infrastructure.persistence.exposed.table.PermitTable
import org.jetbrains.exposed.sql.insertAndGetId
import org.springframework.stereotype.Repository

@Repository
class PermitExposedRepository : PermitRepository {
    override fun save(permit: Permit): Permit {
        val registered: Long =
            PermitTable
                .insertAndGetId {
                    it[complexId] = permit.complexId
                    it[parkingLotId] = permit.parkingLotId
                    it[type] = permit.type
                    it[lpn] = permit.vehicle.licensePlate.number.value
                    it[reversedLpn] =
                        permit.vehicle.licensePlate.number.value
                            .reversed()
                    it[owner] = permit.vehicle.owner.name
                    it[contact] = permit.vehicle.owner.contact
                    it[dong] = permit.destination.dong.value
                    it[ho] = permit.destination.ho.value
                    it[remarks] = permit.remarks
                    it[validFrom] = permit.validFrom
                    it[validTo] = permit.validTo
                    it[registeredAt] = permit.registeredAt
                    it[registeredBy] = permit.registeredBy
                }.value

        return permit.copy(id = registered)
    }

    override fun findByComplexIdAndParkingLotIdAndLicensePlateNumber(
        complexId: Long,
        parkingLotId: Long,
        licensePlateNumber: LicensePlateNumber,
    ): Permit? {
        TODO()
    }
}
