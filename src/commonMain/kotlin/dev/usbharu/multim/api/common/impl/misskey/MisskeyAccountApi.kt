package dev.usbharu.multim.api.common.impl.misskey

import dev.usbharu.multim.api.common.AccountApi
import dev.usbharu.multim.converter.misskey.v12.NoteConverter.toStatus
import dev.usbharu.multim.converter.misskey.v12.UsersConverter.toRelation
import dev.usbharu.multim.model.common.*
import dev.usbharu.multim.model.common.impl.misskey.MisskeyAccount
import dev.usbharu.multim.model.common.impl.misskey.MisskeyProfile
import dev.usbharu.multim.model.common.impl.misskey.MisskeyProfileContent
import dev.usbharu.multim.model.common.impl.misskey.MisskeyStatusId
import dev.usbharu.multim.model.misskey.v12.*
import dev.usbharu.multim.model.misskey.v12.components.MeDetailed
import dev.usbharu.multim.model.misskey.v12.components.UserDetailedNotMe
import dev.usbharu.multim.model.misskey.v12.components.UserLite

class MisskeyAccountApi(val misskeyApis: MisskeyApis) : AccountApi {
    override suspend fun userTimeline(
        account: Account,
        since: StatusId?,
        until: StatusId?
    ): List<Status> {
        if (account is MisskeyAccount && since is MisskeyStatusId && until is MisskeyStatusId) {
            return misskeyApis.users.notes(
                UsersNotesRequest(
                    userId = account.id,
                    sinceId = since.id,
                    untilId = until.id
                )
            ).map { it.toStatus() }

        } else {
            TODO()
        }
    }

    override suspend fun follow(account: Account): Boolean {
        if (account is MisskeyAccount) {
            misskeyApis.following.create(FollowingCreateRequest(account.id))
            return true
        } else {
            TODO()
        }
    }

    override suspend fun unfollow(account: Account): Boolean {
        return if (account is MisskeyAccount) {
            misskeyApis.following.delete(FollowingDeleteRequest(account.id))
            true
        } else {
            // サーバーが認知していないアカウントのフォローを辞めることはできないのでfalse
            false
        }
    }

    override suspend fun profile(account: Account): Profile {
        if (account is MisskeyAccount) {
            return when (val show = misskeyApis.users.show(UsersShowRequest(account.id))) {
                //todo converterで書く
                is MeDetailed -> MisskeyProfile(
                    account,
                    account.isBot ?: false,
                    MisskeyProfileContent(show.description ?: ""),
                    show.followingCount,
                    show.followersCount,
                )
                is UserDetailedNotMe -> MisskeyProfile(
                    account,
                    account.isBot ?: false,
                    MisskeyProfileContent(
                        show.description ?: ""
                    ),
                    show.followingCount,
                    show.followersCount
                )
                is UserLite -> throw IllegalStateException()
                else -> throw IllegalStateException("else")
            }
        }
        TODO("Not yet implemented")
    }

    override suspend fun statuses(account: Account, includeRepost: Boolean): List<Status> {
        if (account is MisskeyAccount) {
            return misskeyApis.users.notes(
                UsersNotesRequest(
                    userId = account.id,
                    includeMyRenotes = includeRepost
                )
            ).map { it.toStatus() }
        } else {
            TODO()
        }
    }

    override suspend fun relationships(myself: Account, other: Account): Relation {
        if (myself is MisskeyAccount && other is MisskeyAccount) {
            return misskeyApis.users.relation(UsersRelationRequest(other.id))
                .toRelation(myself, other)
        }
        TODO()
    }

    override suspend fun requestCancel(account: Account): Boolean {
        if (account is MisskeyAccount) {
            misskeyApis.following.Requests().cancel(FollowingRequestsCancelRequest(account.id))
            return true
        }
        TODO("Not yet implemented")
    }

    override suspend fun requestAccept(account: Account): Boolean {
        if (account is MisskeyAccount) {
            misskeyApis.following.Requests().accept(FollowingRequestsAcceptRequest(account.id))
            return true
        } else {
            TODO()
        }
    }

    override suspend fun requestReject(account: Account): Boolean {
        if (account is MisskeyAccount) {
            misskeyApis.following.Requests().reject(FollowingRequestsRejectRequest(account.id))
            return true
        } else {
            TODO()
        }
    }
}
