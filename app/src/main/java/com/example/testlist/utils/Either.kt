package com.example.testlist.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

typealias EitherE<R> = Either<Exception, R>

sealed class Either<out L, out R> {

    companion object {
        inline fun <R> of(action: () -> R): EitherE<R> {
            return try {
                Right(action())
            } catch (ex: Exception) {
                ex.printStackTrace()
                Left(ex)
            } catch (th: Throwable) {
                Left(RuntimeException(th))
            }
        }
    }
}

data class Right<out R>(val v: R) : Either<Nothing, R>()
data class Left<out L>(val v: L) : Either<L, Nothing>()

suspend fun <T> io(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> T
) = Either.of { withContext(dispatcher) { block() } }