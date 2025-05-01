package me.choicore.samples.pms.infrastructure.persistence.exposed.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.SECONDS

object EntryTransactionTable : LongIdTable(
    name = "entry_transaction",
    columnName = "entry_transaction_id",
) {
    val complexId = long("complex_id")
    val parkingLotId = long("parking_lot_id")
    val token = varchar("token", 32)
    val lpn = varchar("lpn", 9)
    val reversedLpn = varchar("reversed_lpn", 9)
    val dong = varchar("dong", 20)
    val ho = varchar("ho", 20)
    val enteredAt = datetime("entered_at")
    val registeredAt = datetime("registered_at").clientDefault { LocalDateTime.now().truncatedTo(SECONDS) }

    init {
        uniqueIndex(customIndexName = "unique_entry_transaction", columns = arrayOf(complexId, parkingLotId, token))
    }
}
