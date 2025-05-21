package me.choicore.samples.security

interface Introspection {
    val active: Boolean
    val clientId: String
    val scope: String
    val username: String
    val exp: Long
    val iat: Long
    val sub: String
    val aud: String
    val iss: String
}
