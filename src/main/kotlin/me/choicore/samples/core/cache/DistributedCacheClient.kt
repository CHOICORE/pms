package me.choicore.samples.core.cache

import com.fasterxml.jackson.databind.ObjectMapper
import me.choicore.samples.core.TypeReference
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class DistributedCacheClient(
    private val objectMapper: ObjectMapper,
    private val stringRedisTemplate: StringRedisTemplate,
) {
    fun <T : Any> get(
        key: String,
        typeReference: TypeReference<T>,
    ): T? {
        val value: String = stringRedisTemplate.opsForValue().get(key) ?: return null
        return objectMapper.readValue(value, objectMapper.typeFactory.constructType(typeReference.type))
    }

    fun <T : Any> get(
        key: String,
        clazz: Class<T>,
    ): T? {
        val value: String = stringRedisTemplate.opsForValue().get(key) ?: return null
        return objectMapper.readValue(value, clazz)
    }

    fun <T : Any> put(
        key: String,
        value: T,
    ) {
        put(key = key, value = value, ttl = Duration.ZERO)
    }

    fun <T : Any> put(
        key: String,
        value: T,
        ttl: Duration,
    ) {
        val json: String = objectMapper.writeValueAsString(value)
        if (ttl.isZero) {
            stringRedisTemplate.opsForValue().set(key, json)
        } else {
            stringRedisTemplate.opsForValue().set(key, json, ttl)
        }
    }

    fun evict(key: String) {
        stringRedisTemplate.delete(key)
    }
}
