package me.choicore.samples.pms.domain

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class PassFinder(
    private val passRepository: PassRepository,
) {
    fun find(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Pass? {
        val found: Pass? =
            passRepository
                .findByComplexIdAndParkingLotIdAndToken(
                    complexId = complexId,
                    parkingLotId = parkingLotId,
                    token = token,
                )

        return found
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(PassFinder::class.java)
    }
}
