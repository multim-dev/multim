package dev.usbharu.multim.misskey.v12.common.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.map
import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.TODO
import dev.usbharu.multim.misskey.v12.api.MisskeyApis
import dev.usbharu.multim.misskey.v12.common.*
import dev.usbharu.multim.misskey.v12.converter.misskey.v12.NoteConverter.toStatus
import dev.usbharu.multim.misskey.v12.converter.misskey.v12.UsersConverter.toRelation
import dev.usbharu.multim.misskey.v12.model.*
import dev.usbharu.multim.misskey.v12.model.components.MeDetailed
import dev.usbharu.multim.misskey.v12.model.components.UserDetailedNotMe
import dev.usbharu.multim.misskey.v12.model.components.UserLite
import dev.usbharu.multim.model.*

class MisskeyAccountApi(val misskeyApis: MisskeyApis) : AccountApi {
    override suspend fun userTimeline(
        account: Account,
        since: StatusId?,
        until: StatusId?
    ): Result<List<Status>, MultiMError> {
        if (account is MisskeyAccount && since is MisskeyStatusId? && until is MisskeyStatusId?) {
            return misskeyApis.users.notes(
                UsersNotesRequest(
                    userId = account.id,
                    sinceId = since?.id,
                    untilId = until?.id
                )
            ).map {
                it.map { note ->
                    note.toStatus()
                }
            }

        } else {
            return TODO()
        }
    }

    override suspend fun follow(account: Account): Result<Unit, MultiMError> {
        return if (account is MisskeyAccount) {
            misskeyApis.following.create(FollowingCreateRequest(account.id)).map { }
        } else {
            TODO()
        }
    }

    override suspend fun unfollow(account: Account): Result<Unit, MultiMError> {
        return if (account is MisskeyAccount) {
            misskeyApis.following.delete(FollowingDeleteRequest(account.id)).map { }
        } else {
            // サーバーが認知していないアカウントのフォローを辞めることはできないのでfalse
            Err(MultiMError("Can not unfollow", null, ErrorType.API))
        }
    }

    override suspend fun profile(account: Account): Result<Profile, MultiMError> {
        if (account is MisskeyAccount) {
            val show = misskeyApis.users.show(UsersShowRequest(account.id))
            return show.map {
                when (it) {
                    //todo converterで書く
                    is MeDetailed -> MisskeyProfile(
                        account,
                        account.isBot ?: false,
                        MisskeyContent(it.description ?: ""),
                        it.followingCount,
                        it.followersCount,
                    )

                    is UserDetailedNotMe -> MisskeyProfile(
                        account,
                        account.isBot ?: false,
                        MisskeyContent(
                            it.description ?: ""
                        ),
                        it.followingCount,
                        it.followersCount
                    )

                    is UserLite -> throw IllegalStateException()
                }
            }
        }
        return TODO()
    }

    override suspend fun statuses(
        account: Account,
        includeRepost: Boolean
    ): Result<List<MisskeyStatus>, MultiMError> {
        return if (account is MisskeyAccount) {
            misskeyApis.users.notes(
                UsersNotesRequest(
                    userId = account.id,
                    includeMyRenotes = includeRepost
                )
            ).map { it.map { note -> note.toStatus() } }
        } else {
            TODO()
        }
    }

    override suspend fun relationships(
        myself: Account,
        other: Account
    ): Result<Relation, MultiMError> {
        if (myself is MisskeyAccount && other is MisskeyAccount) {
            return misskeyApis.users.relation(UsersRelationRequest(other.id))
                .map { it.toRelation(myself, other) }
        }
        return TODO()
    }

    override suspend fun requestCancel(account: Account): Result<Unit, MultiMError> {
        if (account is MisskeyAccount) {
            misskeyApis.following.Requests().cancel(FollowingRequestsCancelRequest(account.id))
            return Ok(Unit)
        }
        return TODO()
    }

    override suspend fun requestAccept(account: Account): Result<Unit, MultiMError> {
        return if (account is MisskeyAccount) {
            misskeyApis.following.Requests().accept(FollowingRequestsAcceptRequest(account.id))
            Ok(Unit)
        } else {
            TODO()
        }
    }

    override suspend fun requestReject(account: Account): Result<Unit, MultiMError> {
        return if (account is MisskeyAccount) {
            misskeyApis.following.Requests().reject(FollowingRequestsRejectRequest(account.id))
            Ok(Unit)
        } else {
            TODO()
        }
    }
}
