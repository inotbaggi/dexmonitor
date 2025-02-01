package me.baggi.dex

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.types.ParseMode
import eu.vendeli.tgbot.types.internal.LogLvl
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import me.baggi.dex.api.DEXApi
import me.baggi.dex.model.DEXResponse
import me.baggi.dex.util.*
import java.time.Duration
import kotlin.system.exitProcess

object App {
    private val bot = TelegramBot(System.getenv("TELEGRAM_BOT_TOKEN") ?: run { exitProcess(152) }) {
        logging {
            botLogLevel = LogLvl.WARN
        }
    }

    val json = Json {
        ignoreUnknownKeys = true
    }

    val BOT_OWNER_ID = System.getenv("BOT_OWNER_ID") ?: run { exitProcess(153) }
    private val TOKEN = System.getenv("TOKEN") ?: run { exitProcess(154) }
    private val CHAIN = System.getenv("CHAIN") ?: run { exitProcess(155) }

    lateinit var actualTokenInfo: DEXResponse

    @JvmStatic
    fun main(args: Array<String>) {
        KorosTimerTask.start(
            "refresh_token_info",
            Duration.ofSeconds(0),
            Duration.ofSeconds(30),
        ) {
            try {
                actualTokenInfo = DEXApi.getTokenInfo(CHAIN, TOKEN)[0]
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        KorosTimerTask.start(
            "hot_notifications",
            Duration.ofSeconds(0),
            Duration.ofMinutes(5)
        ) {
            val priceChange6h = actualTokenInfo.priceChange["h6"] ?: 0.0
            val priceChange5m = actualTokenInfo.priceChange["m5"] ?: 0.0
            val transactions5m = actualTokenInfo.countTransactions["m5"]

            if (priceChange6h >= 2.0 || priceChange6h <= -2.0) {
                message(
                    priceChanged(priceChange6h, actualTokenInfo.priceUsd, actualTokenInfo.marketCap)
                ).options {
                    parseMode = ParseMode.Markdown
                }.send(BOT_OWNER_ID, bot)
            }

            if (priceChange5m >= 5.0) {
                message(
                    priceSurge(priceChange5m, actualTokenInfo.priceUsd)
                ).options {
                    parseMode = ParseMode.Markdown
                }.send(BOT_OWNER_ID, bot)
            }

            if (priceChange5m <= -5.0) {
                message(
                    priceDrop(priceChange5m, actualTokenInfo.priceUsd)
                ).options {
                    parseMode = ParseMode.Markdown
                }.send(BOT_OWNER_ID, bot)
            }

            if (transactions5m != null && transactions5m.sells > transactions5m.buys * 2) {
                message(
                    highSellVolume(transactions5m.buys, transactions5m.sells)
                ).options {
                    parseMode = ParseMode.Markdown
                }.send(BOT_OWNER_ID, bot)
            }

            if ((actualTokenInfo.volume["h1"] ?: 0.0) > 50_000.0) {
                message(
                    highTradeVolume(actualTokenInfo.volume["h1"]!!)
                ).options {
                    parseMode = ParseMode.Markdown
                }.send(BOT_OWNER_ID, bot)
            }
        }

        KorosTimerTask.start(
            "one_hour_notification",
            Duration.ofSeconds(0),
            Duration.ofHours(1)
        ) {
            message("⏲️ Статистика за час: \n\n${tokenInfoMessage(actualTokenInfo)}").options {
                parseMode = ParseMode.Markdown
            }.send(BOT_OWNER_ID, bot)
        }

        runBlocking {
            bot.handleUpdates()
        }
    }
}