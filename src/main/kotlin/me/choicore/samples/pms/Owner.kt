package me.choicore.samples.pms

data class Owner(
    val name: String? = null,
    val contact: String? = null,
) {
    companion object {
        val UNKNOWN = Owner()
    }
}
