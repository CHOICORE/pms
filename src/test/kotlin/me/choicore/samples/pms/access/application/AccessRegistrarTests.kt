package me.choicore.samples.pms.access.application

import me.choicore.samples.common.util.truncateToSeconds
import me.choicore.samples.pms.AccessDirection
import me.choicore.samples.pms.Destination
import me.choicore.samples.pms.Dong
import me.choicore.samples.pms.Ho
import me.choicore.samples.pms.LicensePlateNumber
import me.choicore.samples.pms.Vehicle
import me.choicore.samples.pms.access.domain.Access
import me.choicore.samples.pms.authorization.domain.Token
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class AccessRegistrarTests(
    private val accessRegistrar: AccessRegistrar,
) {
    @Test
    fun t1() {
        val unknown =
            Access.Unknown(
                complexId = 1,
                parkingLotId = 1,
                vehicle = Vehicle(LicensePlateNumber(value = "123가4567")),
                direction = AccessDirection.IN,
                accessedAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
            )

        accessRegistrar.register(access = unknown)
    }

    @Test
    fun t2() {
        val entry =
            Access.Entry(
                complexId = 1,
                parkingLotId = 1,
                token = Token.Companion.generate(),
                vehicle = Vehicle(LicensePlateNumber(value = "123가4567")),
                destination = Destination(Dong("101"), Ho("202")),
                enteredAt = LocalDateTime.now().truncateToSeconds(),
            )

        accessRegistrar.register(access = entry)
    }

    @Test
    fun t3() {
        val exit =
            Access.Exit(
                complexId = 1,
                parkingLotId = 1,
                token = Token.Companion.generate(),
                vehicle = Vehicle(LicensePlateNumber(value = "123가4567")),
                exitedAt = LocalDateTime.now().truncateToSeconds(),
            )

        accessRegistrar.register(access = exit)
    }
}
