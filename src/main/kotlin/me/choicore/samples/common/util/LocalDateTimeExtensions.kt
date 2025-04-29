package me.choicore.samples.common.util

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.SECONDS

fun LocalDateTime.truncateToSeconds(): LocalDateTime = this.truncatedTo(SECONDS)

fun LocalDateTime.nowWithoutNanos(): LocalDateTime = LocalDateTime.now().truncatedTo(SECONDS)
