package me.choicore.samples.pms.domain

interface PassRepository {
    fun findByComplexIdAndParkingLotIdAndToken(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Pass?

    fun save(pass: Pass): Pass
}
