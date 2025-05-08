package me.choicore.samples.pms.infrastructure.persistence.exposed.table

import me.choicore.samples.pms.domain.ParkingPass
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.SECONDS

object ParkingPassTable : LongIdTable(
    name = "parking_pass",
    columnName = "parking_pass_id",
) {
    val complexId = long("complex_id")
    val parkingLotId = long("parking_lot_id")
    val type = enumerationByName("pass_type", 20, ParkingPass.Type::class)
    val dong = varchar("dong", 20)
    val ho = varchar("ho", 20)
    val lpn = varchar("lpn", 9)
    val reversedLpn = varchar("reversed_lpn", 9)
    val driverName = varchar("driver_name", 50).nullable()
    val driverContact = varchar("driver_contact", 20).nullable()
    val applicantName = varchar("applicant_name", 50).nullable()
    val applicantContact = varchar("applicant_contact", 20).nullable()
    val validFrom = datetime("valid_from").nullable()
    val validTo = datetime("valid_to").nullable()
    val remarks = varchar("remarks", 100).nullable()
    val registeredAt = datetime("registered_at").clientDefault { LocalDateTime.now().truncatedTo(SECONDS) }
    val registeredBy = varchar("registered_by", 50)
}
