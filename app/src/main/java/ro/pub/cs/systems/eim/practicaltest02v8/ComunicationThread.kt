package ro.pub.cs.systems.eim.practicaltest02v8

import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.Socket
import java.net.URL

class CommunicationThread(
    private val socket: Socket
) : Thread() {

    override fun run() {

        var writer = Utilities.getWriter(socket)

        try {
            val reader = Utilities.getReader(socket)

            val url = reader.readLine()
            Log.i(Constants.TAG, "Received URL: $url")

            if (url.isNullOrEmpty()) {
                writer.println("Invalid URL")
                return
            }

            // REGULA FIREWALL
            if (url.contains("bad")) {
                writer.println(Constants.BLOCKED_MESSAGE)
                return
            }

            // URL permis → request HTTP
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.requestMethod = "GET"

            val response = conn.inputStream.bufferedReader().use { it.readText() }

            writer.println(response)

        } catch (e: Exception) {
            writer.println("Error: ${e.message}")
        } finally {
            try { socket.close() } catch (_: IOException) {}
        }
    }
}
