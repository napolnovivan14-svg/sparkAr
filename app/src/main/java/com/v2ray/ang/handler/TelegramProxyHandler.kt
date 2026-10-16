package com.v2ray.ang.handler

import android.content.Context
import android.content.Intent
import android.net.Uri

object TelegramProxyHandler {
    fun launchTelegramWithProxy(context: Context) {
        val localPort = 1080
        // Запускаем whiteAr (ByeDPI) на локальном порту
        WhiteArService.start(context, localPort)

        // Ссылка для мгновенного подключения прокси в Telegram
        val tgProxyUrl = "tg://socks?server=127.0.0.1&port=$localPort"
        
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tgProxyUrl)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}
