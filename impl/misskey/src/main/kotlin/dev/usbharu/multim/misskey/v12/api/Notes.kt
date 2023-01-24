package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.model.*

class Notes(val client: MisskeyApiClient) {

    suspend fun globalTimeline(globalTimelineRequest: NotesGlobalTimelineRequest = NotesGlobalTimelineRequest()): NotesGlobalTimelineResponse {
        return client.post(globalTimelineRequest, "api/notes/global-timeline")
    }

    suspend fun hybridTimeline(hybridTimelineRequest: NotesHybridTimelineRequest = NotesHybridTimelineRequest()): NotesHybridTimelineResponse {
        return client.post(hybridTimelineRequest, "api/notes/hybrid-timeline")
    }

    suspend fun localTimeline(localTimelineRequest: NotesLocalTimelineRequest = NotesLocalTimelineRequest()): NotesLocalTimelineResponse {
        return client.post(localTimelineRequest, "api/notes/local-timeline")
    }

    suspend fun show(showRequest: NotesShowRequest): NotesShowResponse {
        return client.post(showRequest, "api/notes/show")
    }

    suspend fun create(createRequest: NotesCreateRequest): NotesCreateResponse {
        return client.post(createRequest, "api/notes/create")
    }

    suspend fun delete(deleteRequest: NotesDeleteRequest) {
        client.postWithoutResponse(deleteRequest, "api/notes/delete")
    }

    suspend fun featured(featuredRequest: NotesFeaturedRequest = NotesFeaturedRequest()): NotesFeaturedResponse {
        return client.post(featuredRequest, "api/notes/featured")
    }

    suspend fun mentions(mentionsRequest: NotesMentionsRequest): NotesMentionsResponse {
        return client.post(mentionsRequest, "api/notes/mentions")
    }

    suspend fun children(childrenRequest: NotesChildrenRequest): NotesChildrenResponse {
        return client.post(childrenRequest, "api/notes/children")
    }

    suspend fun notes(notesRequest: NotesNotesRequest): NotesNotesResponse {
        return client.post(notesRequest, "api/notes")
    }

    suspend fun conversation(conversationRequest: NotesConversationRequest): NotesConversationResponse {
        return client.post(conversationRequest, "api/notes/conversation")
    }

    suspend fun reactions(reactionsRequest: NotesReactionsRequest): NotesReactionsResponse {
        return client.post(reactionsRequest, "api/notes/reactions")
    }

    suspend fun renotes(renoteRequest: NotesRenoteRequest): NotesRenoteResponse {
        return client.post(renoteRequest, "api/notes/renotes")
    }

    suspend fun replies(repliesRequest: NotesRepliesRequest): NotesRepliesResponse {
        return client.post(repliesRequest, "api/notes/replies")
    }

    suspend fun searchByTag(searchByTagRequest: NotesSearchByTagRequest): NotesSearchByTagResponse {
        return client.post(searchByTagRequest, "api/notes/search-by-tag")
    }

    suspend fun search(searchRequest: NotesSearchRequest): NotesSearchResponse {
        return client.post(searchRequest, "api/notes/search")
    }

    suspend fun state(stateRequest: NotesStateRequest): NotesStateResponse {
        return client.post(stateRequest, "api/notes/state")
    }

    suspend fun timeline(timelineRequest: NotesTimelineRequest): NotesTimelineResponse {
        return client.post(timelineRequest, "api/notes/timeline")
    }

    suspend fun translate(translateRequest: NotesTranslateRequest): NotesTranslateResponse {
        return client.post(translateRequest, "api/notes/translate")
    }

    suspend fun unrenote(unrenoteRequest: NotesUnrenoteRequest) {
        return client.postWithoutResponse(unrenoteRequest, "api/notes/unrenote")
    }

    suspend fun userListTimeline(userListTimelineRequest: NotesUserListTimelineRequest): NotesUserListTimelineResponse {
        return client.post(userListTimelineRequest, "api/notes/user-list-timeline")
    }


    inner class Polls {
        suspend fun recommendation(pollsRecommendationRequest: PollsRecommendationRequest): PollsRecommendationResponse {
            return client.post(pollsRecommendationRequest, "api/notes/polls/recommendation")
        }

        suspend fun vote(voteRequest: NotesPollsVoteRequest): NotesPollsVoteRequest {
            return client.post(voteRequest, "api/notes/polls/vote")
        }
    }

    inner class ThreadMuting {
        suspend fun create(threadMutingCreateRequest: NotesThreadMutingCreateRequest) {
            return client.postWithoutResponse(
                threadMutingCreateRequest,
                "api/notes/thread-muting/create"
            )
        }

        suspend fun delete(threadMutingDeleteRequest: NotesThreadMutingDeleteRequest) {
            return client.postWithoutResponse(
                threadMutingDeleteRequest,
                "api/notes/thread-muting/delete"
            )
        }
    }

    inner class Watching {
        suspend fun create(watchingCreateRequest: NotesWatchingCreateRequest) {
            return client.postWithoutResponse(watchingCreateRequest, "api/notes/watching/create")
        }

        suspend fun delete(watchingDeleteRequest: NotesWatchingDeleteRequest) {
            return client.postWithoutResponse(watchingDeleteRequest, "api/notes/watching/delete")
        }
    }

    inner class Favorites {
        suspend fun create(favoritesCreateRequest: NotesFavoritesCreateRequest) {
            return client.postWithoutResponse(favoritesCreateRequest, "api/notes/favorites/create")
        }

        suspend fun delete(favoritesDeleteRequest: NotesFavoritesDeleteRequest) {
            return client.postWithoutResponse(favoritesDeleteRequest, "api/notes/favorites/delete")
        }
    }

    inner class Reaction {
        suspend fun create(reactionCreateRequest: NotesReactionCreateRequest) {
            return client.postWithoutResponse(reactionCreateRequest, "api/notes/reactions/create")
        }

        suspend fun delete(reactionDeleteRequest: NotesReactionDeleteRequest) {
            return client.postWithoutResponse(reactionDeleteRequest, "api/notes/reactions/delete")
        }
    }
}
