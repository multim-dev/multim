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

    fun <T :Any> cache(method: String, key: String, value: T): T

    fun <T> get(method: String, key: String): T?
    suspend fun <T:Any> cacheOrGet(method: String, key: String, block: suspend () -> T): T {
        val get = get<T>(method, key)
        if (get != null) {
            return get
        }
        return cache(method, key, block())
    }

    suspend fun <T:Any> cacheOrGet(
        method: String,
        vararg objects: Cacheable?,
        block: suspend () -> T
    ): T {
        val key = generateKey(*objects)
        val get = get<T>(method, key)
        if (get != null) {
            return get
        }
        return cache(method, key, block())
    }
}
