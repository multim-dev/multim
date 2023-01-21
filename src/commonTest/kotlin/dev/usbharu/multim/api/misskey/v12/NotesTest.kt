package dev.usbharu.multim.api.misskey.v12

import dev.usbharu.multim.api.common.TestUtil.createFakeNote
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NotesTest {

    @Test
    fun globalTimelineTest() = runTest {
//        val notes = Notes("","" ,TestUtil.createMockHttpClient(content = Json.encodeToString(expectNoteArray)))
//        val globalTimeline =
//            notes.globalTimeline(globalTimelineRequest = NotesGlobalTimelineRequest())
//        assertEquals(expectNoteArray,globalTimeline)
    }

    @Test
    fun hybridTimelineTest() = runTest {
//        val notes = Notes("","", TestUtil.createMockHttpClient(content = Json.encodeToString(expectNoteArray)))
//        val hybridTimeline = notes.hybridTimeline(NotesHybridTimelineRequest())
//        assertEquals(expectNoteArray,hybridTimeline)
    }

    @Test
    fun ldttest() {
        val parse = Instant.parse("2023-01-17T06:58:08.000Z")
        println(parse)
    }


    val expectNoteArray = listOf(
        createFakeNote(
            "4wT4lSP",
            "p9hdK",
            "oYDd",
            "Lorem ipsum dolor sit amet illum lorem ut magna elit dolore amet consectetuer est odio sadipscing lobortis stet sea. Consetetur consetetur stet ut aliquyam aliquyam ut nam congue gubergren gubergren kasd lorem tempor amet kasd consetetur ipsum. Dolor iriure sit dolore erat sit diam adipiscing odio ipsum ullamcorper erat et. Duis gubergren est."
        ),
        createFakeNote(
            "bFe7t9M",
            "v9C",
            "T470fU",
            "Lorem ipsum dolor sit amet at amet rebum vel dolor amet erat invidunt sed duo sit facilisis magna. Sed gubergren et sed amet minim diam tempor diam facilisi amet ipsum justo eirmod gubergren aliquyam."
        ),
        createFakeNote(
            "0P5Kd4LD",
            "t9KO12Q",
            "kcK",
            "Lorem ipsum dolor sit amet et eirmod justo et ut sadipscing tempor labore amet hendrerit vero. Tincidunt lobortis esse kasd in amet erat aliquyam nonumy takimata adipiscing sit at tation stet ex duo justo tempor."
        ),
        createFakeNote(
            "dy3055V7",
            "e1swe9O7",
            "9AtX",
            "Lorem ipsum dolor sit amet et. Dolores dolore vero at tempor wisi ipsum delenit tempor gubergren et dignissim."
        ),
        createFakeNote(
            "vEv9",
            "4xTvOwh",
            "T2ES2jYV",
            "Lorem ipsum dolor sit amet duis feugait erat sanctus luptatum consetetur nonumy sed at ea."
        ),
        createFakeNote(
            "427AW",
            "638oy",
            "JEAgxoD",
            "Lorem ipsum dolor sit amet nam rebum aliquyam nonumy lorem eos clita. Diam ea zzril at eos dolor gubergren et placerat et gubergren lorem eirmod amet sea dolor."
        ),
        createFakeNote(
            "tfkV4j",
            "2bu",
            "UxN1",
            "Lorem ipsum dolor sit amet et sit accusam gubergren dolor amet magna vel at vero. Iusto gubergren ut euismod feugiat et laoreet aliquyam autem."
        ),
        createFakeNote(
            "ty67",
            "5o9JgtBR",
            "c99Zv",
            "Lorem ipsum dolor sit amet sanctus dolore sit. Augue dolor et ipsum ipsum clita labore diam sanctus no ipsum est sed ex elitr ut."
        ),
        createFakeNote(
            "JOm2U2ci",
            "198ZRz8",
            "P29WMF9",
            "Lorem ipsum dolor sit amet nonumy elitr ipsum facilisis vel euismod gubergren consequat consetetur volutpat vel labore. Dolores dolor stet tempor."
        ),
        createFakeNote(
            "2Nv8RU7W",
            "6yv5",
            "dHT06",
            "Lorem ipsum dolor sit amet at ipsum vero. Et et eirmod tempor diam et sit duo sed dolores."
        )
    )
}
