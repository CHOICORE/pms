package me.choicore.samples.pms

import java.util.UUID

@JvmInline
value class Token(
    val code: String,
) {
    companion object {
        fun generate(): Token = Token(code = UUID.randomUUID().toString().replace("-", ""))
    }
}
