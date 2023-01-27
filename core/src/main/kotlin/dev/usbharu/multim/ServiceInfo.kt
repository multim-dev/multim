package dev.usbharu.multim

import dev.usbharu.multim.api.ApiClient
import dev.usbharu.multim.api.PlatformApis
import dev.usbharu.multim.factory.MultiMApis
import io.ktor.client.*

/**
 * Service info
 *
 * @property serviceNameMatchRegex マッチするサービス名の正規表現
 * @property versionMatchRegex マッチするバージョンの正規表現
 * @property multiMApis 実装しているAPI
 * @constructor Create empty Service info
 */
abstract class ServiceInfo(
    val serviceNameMatchRegex: Regex,
    val versionMatchRegex: Regex,
    val apiClient:(String,String,HttpClient)->ApiClient,
    val platFormApis:(ApiClient)->PlatformApis,
    val multiMApis: (PlatformApis)->MultiMApis
)
