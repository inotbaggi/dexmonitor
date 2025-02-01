# DexMonitor
DexMonitor — это бот для Telegram, который отслеживает изменение токена на [dexscreener](https://dexscreener.com/)

## 🚀 Основные фишки

- Мониторинг цен токена
- Уведомления о резких изменениях цены (рост/падение)
- Определение аномальной активности (высокий объем торгов, дисбаланс покупок/продаж)
- Автоматическая отправка статистики раз в час

## Стэк
- Kotlin (Serialization/Coroutines)
- [Telegram Bot API](https://github.com/vendelieu/telegram-bot)
- OkHttp

## Deploy
### Jar

- Клонирование репозитория
```
git clone https://github.com/inotbaggi/dexmonitor.git
cd dexmonitor
```
- Сборка
```
gradle build
gradle shadowJar
```
- Запуск

run.sh (для Linux/macOS)
```
export BOT_TOKEN=your_telegram_bot_token
export BOT_OWNER_ID=your_telegram_id
export CHAIN=your_chain
export TOKEN=your_token_address

java -jar build/libs/dexmonitor-1.0-SNAPSHOT-all.jar
```
run.bat (для Windows)
```
SET BOT_TOKEN="your_telegram_bot_token"
SET BOT_OWNER_ID="your_telegram_id"
SET CHAIN="your_chain"
SET TOKEN="your_token_address"

java -jar build/libs/dexmonitor-1.0-SNAPSHOT-all.jar
```
### Docker (мне лень)

## Связь со мной

Для обсёра говнокода и всего остального -> https://inotbaggi.t.me
