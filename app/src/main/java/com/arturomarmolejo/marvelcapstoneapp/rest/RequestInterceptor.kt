package com.arturomarmolejo.marvelcapstoneapp.rest

import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest

class MarvelInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalUrl = request.url

        //Get timestamp
        val ts = System.currentTimeMillis()

        //Generate hash
        val apiKey = "0034da49ca2e8f3baa0653df93495964"
        val privateKey = "d15f73a0a80daca0ca08230c97f36252808a319c"
        val hash = generateHash(ts, privateKey, apiKey)

        //Add timestamp and hash as query parameters to the request
        val url = originalUrl.newBuilder()
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()

        //Build and return the new request
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }

    //Helper function to generate MD5 hash
    private fun generateHash(ts: Long, privateKey: String, apiKey: String): String {
        val message = "$ts$privateKey$apiKey"
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(message.toByteArray())
        return digest.joinToString(""){"02x".format(it)}
    }
}