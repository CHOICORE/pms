package me.choicore.samples.pms.domain

data class Destination(
    val dong: Dong,
    val ho: Ho,
) {
    companion object {
        fun of(
            dong: String,
            ho: String,
        ): Destination = Destination(Dong(dong), Ho(ho))
    }
}
