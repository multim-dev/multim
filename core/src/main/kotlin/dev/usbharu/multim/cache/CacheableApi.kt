package dev.usbharu.multim.cache

import kotlinx.datetime.Clock

interface CacheableApi {

    companion object {
        fun generateKey(vararg objects: Cacheable?): String {
            var key: String = Clock.System.now().toString()
            for (cacheable in objects) {
                key += cacheable?.cacheKey
            }
            return key
        }
    }

    fun purge()
    fun disable()
    fun enable()
    fun withNoCache(block: () -> Unit) {
        disable()
        try {
            block()
        } finally {
            enable()
        }
    }

    fun <T> cache(key: String, value: T): T

    fun <T> get(key: String): T?
    suspend fun <T> cacheOrGet(key: String, block: suspend () -> T): T {
        val get = get<T>(key)
        if (get != null) {
            return get
        }
        return cache(key, block())
    }

    suspend fun <T> cacheOrGet(vararg objects: Cacheable?, block: suspend () -> T): T {
        val key = generateKey(*objects)
        val get = get<T>(key)
        if (get != null) {
            return get
        }
        return cache(key, block())
    }
}
