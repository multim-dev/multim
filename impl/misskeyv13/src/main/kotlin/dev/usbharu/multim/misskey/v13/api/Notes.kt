package dev.usbharu.multim.misskey.v13.api

import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.error.mapMultiMError
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.model.*
import dev.usbharu.multim.misskey.v13.model.components.Note

@Suppress("MemberNameEqualsClassName")
class Notes(val client: MisskeyApiClient) {

    suspend fun globalTimeline(globalTimelineRequest: NotesGlobalTimelineRequest = NotesGlobalTimelineRequest())
            : MultiMResult<NotesGlobalTimelineResponse> {
        return client.post<NotesGlobalTimelineRequest, NotesGlobalTimelineResponse>(
            globalTimelineRequest,
            "api/notes/global-timeline"
        ).mapMultiMError()
    }

    suspend fun hybridTimeline(hybridTimelineRequest: NotesHybridTimelineRequest = NotesHybridTimelineRequest())
            : MultiMResult<NotesHybridTimelineResponse> {
        return client.post<NotesHybridTimelineRequest, NotesHybridTimelineResponse>(
            hybridTimelineRequest,
            "api/notes/hybrid-timeline"
        ).mapMultiMError()
    }

    suspend fun localTimeline(localTimelineRequest: NotesLocalTimelineRequest = NotesLocalTimelineRequest())
            : MultiMResult<NotesLocalTimelineResponse> {
        return client.post<NotesLocalTimelineRequest, NotesLocalTimelineResponse>(
            localTimelineRequest,
            "api/notes/local-timeline"
        ).mapMultiMError()
    }

    suspend fun show(showRequest: NotesShowRequest): MultiMResult<NotesShowResponse> {
        return client.post<NotesShowRequest, NotesShowResponse>(showRequest, "api/notes/show")
            .mapMultiMError()
    }

    suspend fun create(createRequest: NotesCreateRequest): MultiMResult<NotesCreateResponse> {
        return client.post<NotesCreateRequest, NotesCreateResponse>(
            createRequest,
            "api/notes/create"
        ).mapMultiMError()
    }

    suspend fun delete(deleteRequest: NotesDeleteRequest) {
        client.postWithoutResponse(deleteRequest, "api/notes/delete").mapMultiMError()
    }

    suspend fun featured(featuredRequest: NotesFeaturedRequest = NotesFeaturedRequest())
            : MultiMResult<NotesFeaturedResponse> {
        return client.post<NotesFeaturedRequest, NotesFeaturedResponse>(
            featuredRequest,
            "api/notes/featured"
        ).mapMultiMError()
    }

    suspend fun mentions(mentionsRequest: NotesMentionsRequest): MultiMResult<NotesMentionsResponse> {
        return client.post<NotesMentionsRequest, NotesMentionsResponse>(
            mentionsRequest,
            "api/notes/mentions"
        ).mapMultiMError()
    }

    suspend fun children(childrenRequest: NotesChildrenRequest): MultiMResult<NotesChildrenResponse> {
        return client.post<NotesChildrenRequest, NotesChildrenResponse>(
            childrenRequest,
            "api/notes/children"
        ).mapMultiMError()
    }

    suspend fun notes(notesRequest: NotesNotesRequest): MultiMResult<NotesNotesResponse> {
        return client.post<NotesNotesRequest, NotesNotesResponse>(notesRequest, "api/notes")
            .mapMultiMError()
    }

    suspend fun conversation(conversationRequest: NotesConversationRequest): MultiMResult<NotesConversationResponse> {
        return client.post<NotesConversationRequest, NotesConversationResponse>(
            conversationRequest,
            "api/notes/conversation"
        ).mapMultiMError()
    }

    suspend fun reactions(reactionsRequest: NotesReactionsRequest): MultiMResult<NotesReactionsResponse> {
        return client.post<NotesReactionsRequest, NotesReactionsResponse>(
            reactionsRequest,
            "api/notes/reactions"
        ).mapMultiMError()
    }

    suspend fun renotes(renoteRequest: NotesRenoteRequest): MultiMResult<NotesRenoteResponse> {
        return client.post<NotesRenoteRequest, NotesRenoteResponse>(
            renoteRequest,
            "api/notes/renotes"
        ).mapMultiMError()
    }

    suspend fun replies(repliesRequest: NotesRepliesRequest): MultiMResult<NotesRepliesResponse> {
        return client.post<NotesRepliesRequest, NotesRepliesResponse>(
            repliesRequest,
            "api/notes/replies"
        ).mapMultiMError()
    }

    suspend fun searchByTag(searchByTagRequest: NotesSearchByTagRequest): MultiMResult<NotesSearchByTagResponse> {
        return client.post<NotesSearchByTagRequest, NotesSearchByTagResponse>(
            searchByTagRequest,
            "api/notes/search-by-tag"
        ).mapMultiMError()
    }

    suspend fun search(searchRequest: NotesSearchRequest): MultiMResult<NotesSearchResponse> {
        return client.post<NotesSearchRequest, NotesSearchResponse>(
            searchRequest,
            "api/notes/search"
        ).mapMultiMError()
    }

    suspend fun state(stateRequest: NotesStateRequest): MultiMResult<NotesStateResponse> {
        return client.post<NotesStateRequest, NotesStateResponse>(stateRequest, "api/notes/state")
            .mapMultiMError()
    }

    suspend fun timeline(timelineRequest: NotesTimelineRequest): MultiMResult<NotesTimelineResponse> {
        return client.post<NotesTimelineRequest, NotesTimelineResponse>(
            timelineRequest,
            "api/notes/timeline"
        ).mapMultiMError()
    }

    suspend fun translate(translateRequest: NotesTranslateRequest): MultiMResult<NotesTranslateResponse> {
        return client.post<NotesTranslateRequest, NotesTranslateResponse>(
            translateRequest,
            "api/notes/translate"
        ).mapMultiMError()
    }

    suspend fun unrenote(unrenoteRequest: NotesUnrenoteRequest): MultiMResult<Unit> {
        return client.postWithoutResponse(unrenoteRequest, "api/notes/unrenote").mapMultiMError()
    }

    suspend fun userListTimeline(userListTimelineRequest: NotesUserListTimelineRequest)
            : MultiMResult<NotesUserListTimelineResponse> {
        return client.post<NotesUserListTimelineRequest, List<Note>>(
            userListTimelineRequest,
            "api/notes/user-list-timeline"
        ).mapMultiMError()
    }


    inner class Polls {
        suspend fun recommendation(pollsRecommendationRequest: PollsRecommendationRequest)
                : MultiMResult<PollsRecommendationResponse> {
            return client.post<PollsRecommendationRequest, List<Note>>(
                pollsRecommendationRequest,
                "api/notes/polls/recommendation"
            ).mapMultiMError()
        }

        suspend fun vote(voteRequest: NotesPollsVoteRequest): MultiMResult<NotesPollsVoteRequest> {
            return client.post<NotesPollsVoteRequest, NotesPollsVoteRequest>(
                voteRequest,
                "api/notes/polls/vote"
            ).mapMultiMError()
        }
    }

    inner class ThreadMuting {
        suspend fun create(threadMutingCreateRequest: NotesThreadMutingCreateRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(
                threadMutingCreateRequest,
                "api/notes/thread-muting/create"
            ).mapMultiMError()
        }

        suspend fun delete(threadMutingDeleteRequest: NotesThreadMutingDeleteRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(
                threadMutingDeleteRequest,
                "api/notes/thread-muting/delete"
            ).mapMultiMError()
        }
    }

    inner class Watching {
        suspend fun create(watchingCreateRequest: NotesWatchingCreateRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(watchingCreateRequest, "api/notes/watching/create")
                .mapMultiMError()
        }

        suspend fun delete(watchingDeleteRequest: NotesWatchingDeleteRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(watchingDeleteRequest, "api/notes/watching/delete")
                .mapMultiMError()
        }
    }

    inner class Favorites {
        suspend fun create(favoritesCreateRequest: NotesFavoritesCreateRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(favoritesCreateRequest, "api/notes/favorites/create")
                .mapMultiMError()
        }

        suspend fun delete(favoritesDeleteRequest: NotesFavoritesDeleteRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(favoritesDeleteRequest, "api/notes/favorites/delete")
                .mapMultiMError()
        }
    }

    inner class Reaction {
        suspend fun create(reactionCreateRequest: NotesReactionCreateRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(reactionCreateRequest, "api/notes/reactions/create")
                .mapMultiMError()
        }

        suspend fun delete(reactionDeleteRequest: NotesReactionDeleteRequest): MultiMResult<Unit> {
            return client.postWithoutResponse(reactionDeleteRequest, "api/notes/reactions/delete")
                .mapMultiMError()
        }
    }
}
