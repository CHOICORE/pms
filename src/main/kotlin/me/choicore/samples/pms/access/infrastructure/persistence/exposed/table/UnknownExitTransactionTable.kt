package me.choicore.samples.pms.access.infrastructure.persistence.exposed.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object UnknownExitTransactionTable : LongIdTable("unknown_exit_transaction", "unknown_exit_transaction_id") {
    val complexId = long("complex_id")
    val parkingLotId = long("parking_lot_id")
    val lpn = varchar("lpn", 9)
    val reversedLpn = varchar("reversed_lpn", 9)
    val exitedAt = datetime("entered_at")
    val registeredAt = datetime("registered_at").defaultExpression(CurrentDateTime)
}
