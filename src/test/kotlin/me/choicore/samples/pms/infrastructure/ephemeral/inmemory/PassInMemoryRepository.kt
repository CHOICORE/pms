package me.choicore.samples.pms.infrastructure.ephemeral.inmemory

import me.choicore.samples.pms.domain.AccessDecision
import me.choicore.samples.pms.domain.Destination
import me.choicore.samples.pms.domain.Dong
import me.choicore.samples.pms.domain.Ho
import me.choicore.samples.pms.domain.LicensePlate
import me.choicore.samples.pms.domain.LicensePlateNumber
import me.choicore.samples.pms.domain.Pass
import me.choicore.samples.pms.domain.PassRepository
import me.choicore.samples.pms.domain.PassStatus
import me.choicore.samples.pms.domain.Token
import me.choicore.samples.pms.domain.Vehicle
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository

@Primary
@Repository
class PassInMemoryRepository : PassRepository {
    override fun findByComplexIdAndParkingLotIdAndToken(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Pass? =
        Pass(
            token = token,
            complexId = complexId,
            parkingLotId = parkingLotId,
            destination =
                Destination(
                    dong = Dong("101"),
                    ho = Ho("101"),
                ),
            vehicle =
                Vehicle(
                    licensePlate = LicensePlate(number = LicensePlateNumber(value = "123가4567")),
                ),
            decision = AccessDecision.ALLOWED,
            status = PassStatus.ISSUED,
        )

    override fun save(pass: Pass): Pass = pass
}
