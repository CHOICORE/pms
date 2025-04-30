package me.choicore.samples.pms.access.domain

import me.choicore.samples.pms.access.domain.Access.Entry
import me.choicore.samples.pms.access.domain.Access.Exit
import me.choicore.samples.pms.access.domain.Access.Unknown

interface AccessRepository {
    fun save(entry: Entry): Entry

    fun save(exit: Exit): Exit

    fun save(unknown: Unknown): Unknown
}
