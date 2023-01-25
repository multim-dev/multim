package dev.usbharu.multim.model.nodeinfo.wellknown

import kotlinx.serialization.Serializable

@Serializable
data class NodeinfoList(val links: List<NodeinfoLink>) {

    @Serializable
    data class NodeinfoLink(val href: String, val rel: String)
}
