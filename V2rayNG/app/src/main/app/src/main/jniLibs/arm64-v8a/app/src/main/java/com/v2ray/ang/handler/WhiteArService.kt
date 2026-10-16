package com.v2ray.ang.handler

import android.content.Context
import java.io.File
import java.io.IOException

object WhiteArService {
    private var process: Process? = null

    fun start(context: Context, localPort: Int = 1080) {
        if (process != null) return
        try {
            val libDir = context.applicationInfo.nativeLibraryDir
            val binary = File(libDir, "libciadpi.so")
            
            val command = arrayOf(
                binary.absolutePath,
                "--ip", "127.0.0.1",
                "--port", localPort.toString(),
                "--splittls", "3",
                "--disorder"
            )
            
            process = ProcessBuilder(*command)
                .redirectErrorStream(true)
                .start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun stop() {
        try {
            process?.destroy()
            process = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

