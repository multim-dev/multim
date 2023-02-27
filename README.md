# MultiM: Misskey/Mastodon API Client (in development)

[![test in gradle](https://github.com/usbharu/multim/actions/workflows/pr-test.yml/badge.svg)](https://github.com/usbharu/multim/actions/workflows/pr-test.yml)

## これは何? What is this?

Misskey/MastodonのAPIを可能な限り抽象化し、クライアント/Botの開発を簡単にするために作成された、APIクライアントです。99%
Kotlinで作成されており、JVM/Android上で動作します。現在Misskey v12にのみ対応しています。あと名前募集しています。

## 使い方 How to use.

Mavenリポジトリに登録されていないため手動でビルドする必要があります。また、開発中のため使用できない機能があります。

### リポジトリ任意の場所にをクローン

使用するプロジェクトとは別の場所に作成してください。

`git clone https://github.com/usbharu/multim.git`

### Maven Localに公開

`gradlew publishToMavenLocal`

### 使用するリポジトリに追加

```groovy
repositories {
    mavenLocal()
}

dependencies {
    implementation("dev.usbharu:multim-core:$multim_version")
    implementation("dev.usbharu:multim-misskey:$multim_version")
    implementation("dev.usbharu:multim-mastodon:$multim_version")
}
```

### 実行

```kotlin
val client = MultiM.createClient(
    "https://url-to-mastodon-or-misskey-url/",
    "token",
    ServiceInfoFactory(listOf(MisskeyV12Info))
)

client.statusApi.findById(MisskeyStatusId("id", "url-to-note")) //←改良予定
```

すべての共通APIは [core/src/main/kotlin/dev/usbharu/multim/api](https://github.com/usbharu/multim/tree/develop/core/src/main/kotlin/dev/usbharu/multim/api)
にあります。

## 今後の実装予定

- Mastodon対応
- Misskey v13対応
- Fedibird対応
- キャッシュ
-

## 感謝 Thanks for

### GitHub name

- yude Misskey・Mastodonを1から色々教えてくれた。
- pantasystem Android・Misskey APIを教えてくれた。Fedibird招待してくれた。

### Library name

- ktor
- kotlinx-serialization
- kotlinx-datetime
- kotlinx-coroutines
- napier
- kotlin-result
