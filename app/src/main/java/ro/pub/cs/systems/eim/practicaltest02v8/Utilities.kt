package ro.pub.cs.systems.eim.practicaltest02v8

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

object Utilities {

    fun getReader(socket: Socket): BufferedReader =
        BufferedReader(InputStreamReader(socket.getInputStream()))

    fun getWriter(socket: Socket): PrintWriter =
        PrintWriter(socket.getOutputStream(), true)
}
