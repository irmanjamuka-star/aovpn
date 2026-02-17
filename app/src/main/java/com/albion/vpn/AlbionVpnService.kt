package com.albion.vpn

import android.net.VpnService
import android.os.ParcelFileDescriptor
import java.io.FileInputStream
import java.nio.ByteBuffer

class AlbionVpnService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onStartCommand(intent: android.content.Intent?, flags: Int, startId: Int): Int {

        val builder = Builder()
        builder.addAddress("10.0.0.2", 32)
        builder.addRoute("0.0.0.0", 0)

        vpnInterface = builder.establish()

        Thread {
            readPackets()
        }.start()

        return START_STICKY
    }

    private fun readPackets() {
        val inputStream = FileInputStream(vpnInterface!!.fileDescriptor)
        val buffer = ByteBuffer.allocate(32767)

        while (true) {
            val length = inputStream.read(buffer.array())
            if (length > 0) {
                // Di sini nanti kita filter UDP Albion
            }
        }
    }
}
