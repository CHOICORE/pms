package me.choicore.samples.pms.authorization.domain

import java.util.UUID

@JvmInline
value class Token(
    val value: String,
) {
    companion object {
        fun generate(): Token = Token(value = UUID.randomUUID().toString().replace("-", ""))
    }
}
