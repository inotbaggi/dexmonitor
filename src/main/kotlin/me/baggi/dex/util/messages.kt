package me.baggi.fpi.util

import me.baggi.fpi.model.DEXResponse

fun tokenInfoMessage(dexResponse: DEXResponse): String = """
📊 *${dexResponse.baseToken.name}*  
📍 `${dexResponse.baseToken.address}`  

💰 *Цена:*  
- USD/Ton: `${dexResponse.priceUsd}$ / ${dexResponse.priceNative} ton`  
- 💎 Капитализация: `${dexResponse.marketCap}$`  
- 💧 Ликвидность: `${dexResponse.liquidity["usd"]}$`  

📈 *Изменение цены:*  
- ⏳ 5 минут: `${dexResponse.priceChange["m5"]}%`  
- 🕐 1 час: `${dexResponse.priceChange["h1"]}%`  
- ⏳ 6 часов: `${dexResponse.priceChange["h6"]}%`  
- 📅 24 часа: `${dexResponse.priceChange["h24"]}%`  

🛍 *Транзакции (покупка/продажа):*  
- ⏳ 5 минут: `${dexResponse.countTransactions["m5"]?.buys}` купили / `${dexResponse.countTransactions["m5"]?.sells}` продали  
- 🕐 1 час: `${dexResponse.countTransactions["h1"]?.buys}` купили / `${dexResponse.countTransactions["h1"]?.sells}` продали  
- ⏳ 6 часов: `${dexResponse.countTransactions["h6"]?.buys}` купили / `${dexResponse.countTransactions["h6"]?.sells}` продали  
- 📅 24 часа: `${dexResponse.countTransactions["h24"]?.buys}` купили / `${dexResponse.countTransactions["h24"]?.sells}` продали  
""".trimIndent()


fun priceChanged(percent: Double, price: Double, cap: Long): String = """
🔥 Изменение цены на *$percent%*
💰 Нынешняя цена: *$price$*
💎 Капитализация: *$cap$*
""".trimIndent()

fun priceSurge(percent: Double, price: Double): String = """
🚀 *Цена растет!*  
Цена выросла на *$percent%* за 5 минут.  
💰 Текущая цена: *$price$*
""".trimIndent()

fun priceDrop(percent: Double, price: Double): String = """
📉 *Резкое падение цены!*  
Цена снизилась на *$percent%* за 5 минут.  
💰 Текущая цена: *$price$*
""".trimIndent()

fun highSellVolume(buys: Int, sells: Int): String = """
🛑 *Паника на рынке?*  
За последние 5 минут было *$buys покупок* и *$sells продаж* (в 2 раза больше продаж!).  
""".trimIndent()

fun highTradeVolume(volume: Double): String = """
🔄 *Высокий объем торгов!*  
Объем за 1 час: *$volume$*
""".trimIndent()
