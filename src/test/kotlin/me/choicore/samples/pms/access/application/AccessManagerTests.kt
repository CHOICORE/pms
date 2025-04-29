package me.choicore.samples.pms.access.application

import me.choicore.samples.pms.AccessDirection
import me.choicore.samples.pms.authorization.domain.TicketFinder
import me.choicore.samples.pms.authorization.domain.Token
import me.choicore.samples.pms.authorization.infrastructure.ephemeral.inmemory.TicketInMemoryRepository
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AccessManagerTests {
    private val sut =
        AccessManager(ticketFinder = TicketFinder(ticketRepository = TicketInMemoryRepository()))

    @Test
    fun t1() {
        sut.access(
            1,
            1,
            Token.Companion.generate(),
            LocalDateTime.now(),
            AccessDirection.IN,
        )
    }
}
