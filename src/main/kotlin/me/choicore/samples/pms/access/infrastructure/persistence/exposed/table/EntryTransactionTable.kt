package me.choicore.samples.pms.access.infrastructure.persistence.exposed.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object EntryTransactionTable : LongIdTable("entry_transaction", "entry_transaction_id") {
    val complexId = long("complex_id")
    val parkingLotId = long("parking_lot_id")
    val token = varchar("token", 20)
    val lpn = varchar("lpn", 9)
    val reversedLpn = varchar("reversed_lpn", 9)
    val dong = varchar("dong", 20)
    val ho = varchar("ho", 20)
    val enteredAt = datetime("entered_at")
    val registeredBy = varchar("registered_by", 20)
    val registeredAt = datetime("registered_at").defaultExpression(CurrentDateTime)
}
