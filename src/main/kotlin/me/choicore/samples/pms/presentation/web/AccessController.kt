package me.choicore.samples.pms.presentation.web

import me.choicore.samples.pms.application.AccessManager
import me.choicore.samples.pms.domain.AccessDirection
import me.choicore.samples.pms.domain.Token
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping()
class AccessController(
    private val accessManager: AccessManager,
) {
    @PostMapping(value = ["/accesses"], headers = ["X-Pass-Token", "X-Pass-Time", "X-Pass-Direction"])
    fun accesses(
        @RequestHeader("X-Pass-Token") token: Token,
        @RequestHeader("X-Pass-Time") timestamp: LocalDateTime,
        @RequestHeader("X-Pass-Direction") direction: AccessDirection,
    ): ResponseEntity<in Any> {
        val request =
            AccessRequest(
                token = token,
                timestamp = timestamp,
                direction = direction,
            )

        return this.accesses(request = request)
    }

    @PostMapping(value = ["/accesses"])
    fun accesses(
        @RequestBody request: AccessRequest,
    ): ResponseEntity<in Any> {
        accessManager.access(
            complexId = 0L,
            parkingLotId = 0L,
            token = request.token,
            timestamp = request.timestamp,
            direction = request.direction,
        )
        return ResponseEntity.ok(request)
    }

    @PostMapping("/accesses/unknowns")
    fun unknowns(
        @RequestBody request: UnknownAccessRequest,
    ): ResponseEntity<in Any> {
        accessManager.unknown(
            complexId = 0L,
            parkingLotId = 0L,
            licensePlateNumber = request.lpn,
            enteredAt = request.enteredAt,
            exitedAt = request.exitedAt,
        )
        return ResponseEntity.ok(request)
    }
}
