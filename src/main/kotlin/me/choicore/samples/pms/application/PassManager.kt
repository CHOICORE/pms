package me.choicore.samples.pms.application

import me.choicore.samples.pms.domain.Pass
import me.choicore.samples.pms.domain.PassFinder
import me.choicore.samples.pms.domain.Token
import org.springframework.stereotype.Service

@Service
class PassManager(
    private val passFinder: PassFinder,
) {
    fun getPass(
        complexId: Long,
        parkingLotId: Long,
        token: Token,
    ): Pass = passFinder.find(complexId, parkingLotId, token) ?: throw NoSuchElementException("통행권을 찾을 수 없습니다.")
}
