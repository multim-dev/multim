package dev.usbharu.multim.model.misskey.v12

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class ApShowTest {

    @Test
    fun serializeTest() {
        //language=JSON
        val test = """{
  "type": "Note",
  "object": {
    "id": "aaaaaaaaa",
    "createdAt": "2023-01-19T06:07:12.000Z",
    "userId": "aaaaaaaaa",
    "user": {
      "id": "vvvvvvvv",
      "name": "aaa",
      "username": "aaa",
      "host": "example.com",
      "avatarUrl": "https://localhost/fake.webp",
      "avatarBlurhash": "afaldjfaslfjdklajf",
      "avatarColor": null,
      "instance": {
        "name": "test",
        "softwareName": "testdon",
        "softwareVersion": "3.3.3",
        "iconUrl": "https://example.com/favicon.ico",
        "faviconUrl": "https://example.com/favicon.ico",
        "themeColor": "#000000"
      },
      "emojis": [],
      "onlineStatus": "unknown",
      "driveCapacityOverrideMb": null
    },
    "text": "Lorem ipsum dolor sit amet clita et lorem invidunt eirmod elitr soluta vel rebum justo consetetur sed. Tempor option takimata ipsum diam doming est gubergren velit accumsan sed sed consectetuer.",
    "cw": null,
    "visibility": "public",
    "renoteCount": 0,
    "repliesCount": 0,
    "reactions": {},
    "emojis": [],
    "fileIds": [],
    "files": [],
    "replyId": null,
    "renoteId": null,
    "uri": "https://example.com",
    "url": "https://example.com"
  }}"""
        val response: ApShowResponse = Json { ignoreUnknownKeys = true }.decodeFromString(test)
        println(response)
    }
}
