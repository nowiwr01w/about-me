package com.nowiwr01p.me.domain

interface UseCase<T, R> {
    suspend fun execute(input: T): R
}

suspend inline fun <T> UseCase<Unit, T>.execute(): T = execute(Unit)