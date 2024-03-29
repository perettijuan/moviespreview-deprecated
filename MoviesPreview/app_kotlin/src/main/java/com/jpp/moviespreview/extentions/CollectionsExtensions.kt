package com.jpp.moviespreview.extentions


/**
 * Function that iterates over a collection and executes the predicate over each element.
 * When the result of the predicate function is not null, this result is returned.
 * If no element is found (meaning that the result of executing the predicate with each
 * element as parameter always returns null) an exception is thrown.
 */
inline fun <T, R : Any> Iterable<T>.firstResult(predicate: (T) -> R?): R {
    for (element in this) {
        var result = predicate(element)
        if (result != null) return result
    }
    throw NoSuchElementException("No element matching the predicate was found")
}

/**
 * Extension function that iterates over a list and returns the result of applying a lambda (predicate)
 * to the first non null result found. If all the elements on the list are null, the null is returned.
 */
inline fun <V, T : Any> Iterable<V>.findFirstPossibleResult(predicate: (V) -> T?): T? =
        try {
            firstResult { predicate(it) }
        } catch (e: NoSuchElementException) {
            null
        }
