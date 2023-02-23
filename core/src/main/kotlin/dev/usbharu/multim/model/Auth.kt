package dev.usbharu.multim.model

abstract class Auth

class SingleTokenAuth(val token:String): Auth()
