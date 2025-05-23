package me.choicore.samples.pms.application

import me.choicore.samples.pms.domain.AccessDirection
import me.choicore.samples.pms.domain.Token
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class AccessManagerTests(
    private val accessManager: AccessManager,
) {
    @Test
    fun t1() {
        accessManager.access(
            complexId = 1,
            parkingLotId = 1,
            token = Token.generate(),
            timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
            direction = AccessDirection.IN,
        )
    }
}
