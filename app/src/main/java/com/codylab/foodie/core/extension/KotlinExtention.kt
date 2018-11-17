package com.codylab.foodie.core.extension

val <T> T.exhaustive: T
    get() = this

sealed class SealedClass {
    object Child1 : SealedClass()
    object Child2 : SealedClass()
}
fun usage(s: SealedClass) {
    when(s) {
        SealedClass.Child1 -> {
            print("Child 1")
        }
        SealedClass.Child2 -> {

        }
    }.exhaustive
}