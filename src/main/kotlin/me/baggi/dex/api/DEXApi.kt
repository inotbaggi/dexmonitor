package me.baggi.dex.api

import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import me.baggi.dex.App.json
import me.baggi.dex.model.DEXResponse

object DEXApi {
    private val client = OkHttpClient()

    fun getTokenInfo(chain: String, token: String): Array<DEXResponse> {
        val request = Request.Builder()
            .get()
            .url("https://api.dexscreener.com/tokens/v1/$chain/$token")
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw RuntimeException("Error getting token info: ${response.code()} ${response.message()}")

        val body = response.body()!!.string()
        return json.decodeFromString(body)
    }
}