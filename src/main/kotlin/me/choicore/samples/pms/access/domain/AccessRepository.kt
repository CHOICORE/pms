package me.choicore.samples.pms.access.domain

interface AccessRepository {
    fun save(entry: Entry): Entry

    fun save(exit: Exit): Exit

    fun save(unknown: Entry.Unknown): Entry.Unknown

    fun save(unknown: Exit.Unknown): Exit.Unknown
}
