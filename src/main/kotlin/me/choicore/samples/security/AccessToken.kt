package me.choicore.samples.security

@JvmInline
value class AccessToken(
    val value: String,
) {
    companion object {
        const val BEARER_AUTHORIZATION_PREFIX = "Bearer "
    }
}
