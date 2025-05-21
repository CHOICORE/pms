package me.choicore.samples.security

interface Introspector {
    fun introspect(accessToken: AccessToken): Introspection
}
