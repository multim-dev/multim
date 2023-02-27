package dev.usbharu.multim.cache

import io.github.reactivecircus.cache4k.Cache

class InMemoryLRUCache(maxSize: Long) : CacheableApi {
    private val cache = Cache.Builder().maximumCacheSize(maxSize).build<String, Any>()
    private var enable: Boolean = true
    override fun purge() {
        cache.invalidateAll()
    }

    override fun disable() {
        enable = false
    }

    override fun enable() {
        enable = true
    }

    override fun <T : Any> cache(method: String, key: String, value: T): T {
        if (enable) {
            cache.put(method + key, value)
        }
        return value
    }

    override fun <T> get(method: String, key: String): T? {
        if (enable) {
            val any = cache.get(method + key)
            return try {
                any as T?
            } catch (e: ClassCastException) {
                null
            }
        }
        return null
    }
}
