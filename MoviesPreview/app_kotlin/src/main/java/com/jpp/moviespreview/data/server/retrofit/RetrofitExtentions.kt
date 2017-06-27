package com.jpp.moviespreview.data.server.retrofit

import retrofit2.Call

/**
 * Created by jpp on 6/27/17.
 */
inline fun <T, U> Call<T>.unwrapCall(f: (T) -> U): U {
    return f(execute().body())
}