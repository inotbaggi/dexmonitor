package me.baggi.fpi.controller

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.annotations.UpdateHandler
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.types.ParseMode
import eu.vendeli.tgbot.types.User
import eu.vendeli.tgbot.types.internal.MessageUpdate
import eu.vendeli.tgbot.types.internal.UpdateType
import me.baggi.fpi.App
import me.baggi.fpi.util.tokenInfoMessage

object Handler {
    @UpdateHandler([UpdateType.MESSAGE])
    suspend fun catchMessages(
        update: MessageUpdate,
        bot: TelegramBot
    ) {
        println("Catch message: ${update.message.text} (chatid: ${update.message.chat.id} from: ${update.message.from?.id})")
    }

    @CommandHandler(["/info"])
    suspend fun info(user: User, bot: TelegramBot) {
        if (user.id.toString() != App.BOT_OWNER_ID) return

        message(tokenInfoMessage(App.actualTokenInfo)).options {
            parseMode = ParseMode.Markdown
        }.send(user, bot)
    }
}