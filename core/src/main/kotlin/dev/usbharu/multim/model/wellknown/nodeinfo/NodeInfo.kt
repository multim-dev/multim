package dev.usbharu.multim.model.wellknown.nodeinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class NodeInfo(val internalVersion: String){

}


@Serializable
// todo seald classにして基底クラス同じにする。
//  あとついでにversion分ける
data class V2_0(
    val version: String,
    val software: Software,
    val protocols: List<Protocol>,
    val services: Services,
    val openRegistrations: Boolean,
    val usage: Usage
) : NodeInfo("2.0") {
    @Serializable
    data class Software(
        val name: String,
        val version: String
    )

    @Serializable
    enum class Protocol {
        @SerialName("activitypub")
        ACTIVITYPUB,

        @SerialName("buddycloud")
        BUDDYCLOUD,

        @SerialName("dfrn")
        DFRN,

        @SerialName("diaspora")
        DIASPORA,

        @SerialName("libertree")
        LIBERTREE,

        @SerialName("ostatus")
        OSTATUS,

        @SerialName("pumpio")
        PUMPIO,

        @SerialName("tent")
        TENT,

        @SerialName("xmpp")
        XMPP,

        @SerialName("zot")
        ZOT
    }

    @Serializable
    data class Services(
        val inbound: List<Inbound>,
        val outBound: List<Outbound>
    ) {
        @Serializable
        enum class Inbound {
            @SerialName("atom1.0")
            ATOM1,

            @SerialName("gnusocial")
            GNUSOCIAL,

            @SerialName("imap")
            IMAP,

            @SerialName("pnut")
            PNUT,

            @SerialName("pop3")
            POP3,

            @SerialName("pumpio")
            PUMPIO,

            @SerialName("rss2.0")
            RSS2,

            @SerialName("twitter")
            TWITTER,
        }

        @Serializable
        enum class Outbound {
            @SerialName("atom1.0")
            ATOM1,

            @SerialName("blogger")
            BLOGGER,

            @SerialName("buddycloud")
            BUDDYCLOUD,

            @SerialName("diaspora")
            DIASPORA,

            @SerialName("dreamwidth")
            DREAMWIDTH,

            @SerialName("drupal")
            DRUPAL,

            @SerialName("facebook")
            FACEBOOK,

            @SerialName("friendica")
            FRIENDICA,

            @SerialName("gnusocial")
            GNUSOCIAL,

            @SerialName("google")
            GOOGLE,

            @SerialName("insanejournal")
            INSANEJOURNAL,

            @SerialName("libertree")
            LIBERTREE,

            @SerialName("linkedin")
            LINKEDIN,

            @SerialName("livejournal")
            LIVEJOURNAL,

            @SerialName("mediagoblin")
            MEDIAGOBLIN,

            @SerialName("myspace")
            MYSPACE,

            @SerialName("pinterest")
            PINTEREST,

            @SerialName("pnut")
            PNUT,

            @SerialName("posterous")
            POSTEROUS,

            @SerialName("pumpio")
            PUMPIO,

            @SerialName("redmatrix")
            REDMATRIX,

            @SerialName("rss2.0")
            RSS2,

            @SerialName("smtp")
            SMTP,

            @SerialName("tent")
            TENT,

            @SerialName("tumblr")
            TUMBLR,

            @SerialName("twitter")
            TWITTER,

            @SerialName("wordpress")
            WORDPRESS,

            @SerialName("xmpp")
            XMPP
        }
    }

    @Serializable
    data class Usage(
        val users: Users,
        val localPosts: Int,
        val localComments: Int
    ) {
        @Serializable
        data class Users(
            val total: Int,
            val activeHalfyear: Int? = null,
            val activeMonth: Int? = null
        )
    }
}
