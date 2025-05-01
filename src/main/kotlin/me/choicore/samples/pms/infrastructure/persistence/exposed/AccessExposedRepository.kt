package me.choicore.samples.pms.infrastructure.persistence.exposed

import me.choicore.samples.pms.domain.Access.Entry
import me.choicore.samples.pms.domain.Access.Exit
import me.choicore.samples.pms.domain.Access.Unknown
import me.choicore.samples.pms.domain.AccessDirection
import me.choicore.samples.pms.domain.AccessRepository
import me.choicore.samples.pms.infrastructure.persistence.exposed.table.EntryTransactionTable
import me.choicore.samples.pms.infrastructure.persistence.exposed.table.ExitTransactionTable
import me.choicore.samples.pms.infrastructure.persistence.exposed.table.UnknownEntryTransactionTable
import me.choicore.samples.pms.infrastructure.persistence.exposed.table.UnknownExitTransactionTable
import org.jetbrains.exposed.sql.insertAndGetId
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class AccessExposedRepository : AccessRepository {
    @Transactional
    override fun save(entry: Entry): Entry {
        val registered =
            EntryTransactionTable
                .insertAndGetId {
                    it[complexId] = entry.complexId
                    it[parkingLotId] = entry.parkingLotId
                    it[lpn] = entry.vehicle.licensePlate.number.value
                    it[reversedLpn] =
                        entry.vehicle.licensePlate.number.value
                            .reversed()
                    it[token] = entry.token.value
                    it[dong] = entry.destination.dong.value
                    it[ho] = entry.destination.ho.value
                    it[enteredAt] = entry.enteredAt
                }.value
        return entry.copy(id = registered)
    }

    @Transactional
    override fun save(exit: Exit): Exit {
        val registered =
            ExitTransactionTable
                .insertAndGetId {
                    it[complexId] = exit.complexId
                    it[parkingLotId] = exit.parkingLotId
                    it[lpn] = exit.vehicle.licensePlate.number.value
                    it[reversedLpn] =
                        exit.vehicle.licensePlate.number.value
                            .reversed()
                    it[token] = exit.token.value
                    it[exitedAt] = exit.exitedAt
                }.value
        return exit.copy(id = registered)
    }

    @Transactional
    override fun save(unknown: Unknown): Unknown {
        val registered =
            when (unknown.direction) {
                AccessDirection.IN -> {
                    UnknownEntryTransactionTable
                        .insertAndGetId {
                            it[complexId] = unknown.complexId
                            it[parkingLotId] = unknown.parkingLotId
                            it[lpn] = unknown.vehicle.licensePlate.number.value
                            it[reversedLpn] =
                                unknown.vehicle.licensePlate.number.value
                                    .reversed()
                            it[enteredAt] = unknown.accessedAt
                        }
                }

                AccessDirection.OUT -> {
                    UnknownExitTransactionTable
                        .insertAndGetId {
                            it[complexId] = unknown.complexId
                            it[parkingLotId] = unknown.parkingLotId
                            it[lpn] = unknown.vehicle.licensePlate.number.value
                            it[reversedLpn] =
                                unknown.vehicle.licensePlate.number.value
                                    .reversed()
                            it[exitedAt] = unknown.accessedAt
                        }
                }
            }

        return unknown.copy(id = registered.value)
    }
}
