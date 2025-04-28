package me.choicore.samples.pms

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
            Token.generate(),
            LocalDateTime.now(),
            AccessDirection.IN,
        )
    }
}
