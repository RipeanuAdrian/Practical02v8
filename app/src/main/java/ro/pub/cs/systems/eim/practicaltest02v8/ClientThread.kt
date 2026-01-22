package ro.pub.cs.systems.eim.practicaltest02v8

import android.util.Log
import android.widget.TextView
import java.io.IOException
import java.net.Socket

class ClientThread(
    private val address: String,
    private val port: Int,
    private val url: String,
    private val resultTextView: TextView
) : Thread() {

    override fun run() {
        var socket: Socket? = null
        try {
            socket = Socket(address, port)

            val reader = Utilities.getReader(socket)
            val writer = Utilities.getWriter(socket)

            writer.println(url)

            val sb = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                sb.append(line).append("\n")
            }

            val result = sb.toString().trim()

            resultTextView.post {
                resultTextView.text = result
            }

        } catch (e: IOException) {
            Log.e(Constants.TAG, "Client error: ${e.message}")
        } finally {
            try { socket?.close() } catch (_: IOException) {}
        }
    }
}
