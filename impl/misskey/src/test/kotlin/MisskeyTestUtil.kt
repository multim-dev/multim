import dev.usbharu.multim.misskey.v12.model.components.Note
import dev.usbharu.multim.misskey.v12.model.components.UserLite
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import org.assertj.core.api.Fail

object MisskeyTestUtil {

    val json = Json { ignoreUnknownKeys = true;isLenient = true }

    fun createMockHttpClient(
        content: String = "",
        contentType: String = "application/json",
        statusCode: HttpStatusCode = HttpStatusCode.OK,
        checkAuth: Boolean = true,
        respond: suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData = {
            if (checkAuth) {
                if ("i" in json.parseToJsonElement(it.body.toByteArray().decodeToString()).jsonObject) {

                } else {
                    Fail.fail("No auth")
                }
            }
            respond(
                content = content,
                status = statusCode,
                headers = headersOf(HttpHeaders.ContentType, contentType)
            )
        }
    ): HttpClient {
        return HttpClient(MockEngine(respond)) {
            Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    fun checkAuth(
        respond: String,
        status: HttpStatusCode = HttpStatusCode.OK,
        headers: Headers = headersOf()
    ): suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData {
        return { httpRequestData: HttpRequestData ->
            val decodeToString = httpRequestData.body.toByteArray().decodeToString()
            println(decodeToString)
            if ("i" in json.parseToJsonElement(decodeToString).jsonObject) {
                respond(respond, status, headers)
            } else {
                Fail.fail("Not authed")
                respondBadRequest()
            }
        }
    }

    fun createFakeNote(id: String, userId: String, username: String, text: String?): Note {
        return Note(
            id = id,
            userId = userId,
            user = UserLite(id = userId, name = username, username = username),
            text = text
        )
    }

    fun createFakeNoteToString(id: String, userId: String, username: String, text: String?): String {
        return json.encodeToString(createFakeNote(id, userId, username, text))
    }
}
