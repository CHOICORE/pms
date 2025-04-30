package me.choicore.samples.pms.access.domain

import me.choicore.samples.core.event.AbstractEvent

data class AccessRegisteredEvent(
    override val source: Access,
) : AbstractEvent(source = source)
