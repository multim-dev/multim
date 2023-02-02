package dev.usbharu.multim.multi

import kotlin.reflect.KProperty

interface MultiAccountData<T> {

    val data: T
    val hashCode:Int
    operator fun getValue(thisRef: Any?, property: KProperty<*>):T{
        return data
    }
}
