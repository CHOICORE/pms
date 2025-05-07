package me.choicore.samples.pms.infrastructure.persistence.exposed

import me.choicore.samples.pms.domain.Pass
import me.choicore.samples.pms.domain.PassRepository
import me.choicore.samples.pms.domain.Token
import org.springframework.stereotype.Repository

@Repository
class PassExposedRepository : PassRepository {
    override fun findByComplexIdAndParkingLotIdAndToken(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Pass? {
        TODO()
    }

    override fun save(pass: Pass): Pass {
        TODO()
    }
}
