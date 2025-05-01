package me.choicore.samples.pms.domain

import me.choicore.samples.pms.domain.Access.Entry
import me.choicore.samples.pms.domain.Access.Exit
import me.choicore.samples.pms.domain.Access.Unknown

interface AccessRepository {
    fun save(entry: Entry): Entry

    fun save(exit: Exit): Exit

    fun save(unknown: Unknown): Unknown

    fun save(access: Access): Access =
        when (access) {
            is Entry -> save(entry = access)
            is Exit -> save(exit = access)
            is Unknown -> save(unknown = access)
        }
}
