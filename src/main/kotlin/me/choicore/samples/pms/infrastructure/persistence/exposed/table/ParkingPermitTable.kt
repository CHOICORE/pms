package me.choicore.samples.pms.infrastructure.persistence.exposed.table

import me.choicore.samples.pms.domain.ParkingPermit
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.SECONDS

object ParkingPermitTable : LongIdTable(
    name = "parking_permit",
    columnName = "parking_permit_id",
) {
    val complexId = long("complex_id")
    val parkingLotId = long("parking_lot_id")
    val type = enumerationByName("permit_type", 20, ParkingPermit.Type::class)
    val dong = varchar("dong", 20)
    val ho = varchar("ho", 20)
    val lpn = varchar("lpn", 9)
    val reversedLpn = varchar("reversed_lpn", 9)
    val driver = varchar("driver", 50).nullable()
    val contact = varchar("contact", 20).nullable()
    val validFrom = datetime("valid_from").nullable()
    val validTo = datetime("valid_to").nullable()
    val remarks = varchar("remarks", 100).nullable()
    val registeredAt = datetime("registered_at").clientDefault { LocalDateTime.now().truncatedTo(SECONDS) }
    val registeredBy = varchar("registered_by", 50)
}
