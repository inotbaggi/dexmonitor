package me.baggi.dex.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DEXResponse(
    val baseToken: TokenInfo,
    val priceNative: Double,
    val priceUsd: Double,
    @SerialName("txns")
    val countTransactions: Map<String, Transactions>,
    val volume: Map<String, Double>,
    val priceChange: Map<String, Double>,
    val liquidity: Map<String, Double>,
    val fdv: Long,
    val marketCap: Long
)

@Serializable
data class TokenInfo(
    val name: String,
    val symbol: String,
    val address: String,
)

@Serializable
data class Transactions(
    val buys: Int,
    val sells: Int
)
