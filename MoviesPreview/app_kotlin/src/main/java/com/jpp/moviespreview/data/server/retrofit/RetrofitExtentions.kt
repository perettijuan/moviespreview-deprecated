package com.jpp.moviespreview.data.server.retrofit

import retrofit2.Call

/**
 * Extension function to Call that unwraps the result T of the call
 * by applying a lambda expression
 *
 * Created by jpp on 6/27/17.
 */
inline fun <T, U> Call<T>.unwrapCall(f: (T) -> U): U {
    return f(execute().body())
}