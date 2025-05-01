package me.choicore.samples.pms.infrastructure.persistence.exposed.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.SECONDS

object UnknownExitTransactionTable : LongIdTable(
    name = "unknown_exit_transaction",
    columnName = "unknown_exit_transaction_id",
) {
    val complexId = long("complex_id")
    val parkingLotId = long("parking_lot_id")
    val lpn = varchar("lpn", 9)
    val reversedLpn = varchar("reversed_lpn", 9)
    val exitedAt = datetime("exited_at")
    val registeredAt = datetime("registered_at").clientDefault { LocalDateTime.now().truncatedTo(SECONDS) }
}
