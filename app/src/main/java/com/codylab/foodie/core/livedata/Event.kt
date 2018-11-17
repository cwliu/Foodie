package com.codylab.foodie.core.livedata

class Event<out T>(val data: T) {

    var hasBeenHandled = false
        private set

    fun getDataIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            data
        }
    }

    fun peekData(): T = data
}