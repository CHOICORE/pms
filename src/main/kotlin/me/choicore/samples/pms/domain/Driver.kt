package me.choicore.samples.pms.domain

data class Driver(
    val name: String? = null,
    val contact: String? = null,
) {
    companion object {
        val UNKNOWN = Driver()
    }
}
