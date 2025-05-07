package me.choicore.samples.pms.infrastructure.persistence.exposed.table

import me.choicore.samples.pms.domain.Permit
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.SECONDS

object PermitTable : LongIdTable(
    name = "permit",
    columnName = "permit_id",
) {
    val complexId = long("complex_id")
    val parkingLotId = long("parking_lot_id")
    val type = enumerationByName("permit_type", 20, Permit.Type::class)
    val lpn = varchar("lpn", 9)
    val reversedLpn = varchar("reversed_lpn", 9)
    val dong = varchar("dong", 20)
    val ho = varchar("ho", 20)
    val validFrom = datetime("valid_from").nullable()
    val validTo = datetime("valid_to").nullable()
    val owner = varchar("owner", 50).nullable()
    val contact = varchar("contact", 50).nullable()
    val remarks = varchar("remarks", 100).nullable()
    val registeredAt = datetime("registered_at").clientDefault { LocalDateTime.now().truncatedTo(SECONDS) }
    val registeredBy = varchar("registered_by", 50)
}
