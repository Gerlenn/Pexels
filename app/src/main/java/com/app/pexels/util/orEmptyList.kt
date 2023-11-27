package com.app.pexels.util

fun <T> List<T>?.orEmptyList(): List<T> {
    return this ?: emptyList()
}