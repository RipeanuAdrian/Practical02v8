package ro.pub.cs.systems.eim.practicaltest02v8

import android.util.Log
import java.io.IOException
import java.net.ServerSocket

class ServerThread(private val port: Int) : Thread() {

    private var serverSocket: ServerSocket? = null

    override fun run() {
        try {
            serverSocket = ServerSocket(port)
            Log.i(Constants.TAG, "Server started on port $port")

            while (!isInterrupted) {
                val socket = serverSocket!!.accept()
                Log.i(Constants.TAG, "Client connected: ${socket.inetAddress}")

                CommunicationThread(socket).start()
            }
        } catch (e: IOException) {
            Log.e(Constants.TAG, "Server error: ${e.message}")
        }
    }

    fun stopThread() {
        interrupt()
        try {
            serverSocket?.close()
        } catch (_: IOException) {}
    }
}
