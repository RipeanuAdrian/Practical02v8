//package ro.pub.cs.systems.eim.practicaltest02
package ro.pub.cs.systems.eim.practicaltest02v8


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ro.pub.cs.systems.eim.practicaltest02v8.R

class PracticalTest02v8MainActivity : AppCompatActivity() {
    private var serverThread: ServerThread? = null
    private var clientThread: ClientThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test02v8)

        val serverPortEditText = findViewById<EditText>(R.id.server_port_edit_text)
        val startServerButton = findViewById<Button>(R.id.start_server_button)

        val clientAddressEditText = findViewById<EditText>(R.id.client_address_edit_text)
        val clientPortEditText = findViewById<EditText>(R.id.client_port_edit_text)
        val urlEditText = findViewById<EditText>(R.id.url_edit_text)
        val sendButton = findViewById<Button>(R.id.send_button)
        val resultTextView = findViewById<TextView>(R.id.result_text_view)

        startServerButton.setOnClickListener {
            val port = serverPortEditText.text.toString()
            if (port.isNotEmpty()) {
                serverThread = ServerThread(port.toInt())
                serverThread?.start()
            }
        }

        sendButton.setOnClickListener {
            clientThread = ClientThread(
                clientAddressEditText.text.toString(),
                clientPortEditText.text.toString().toInt(),
                urlEditText.text.toString(),
                resultTextView
            )
            clientThread?.start()
        }
    }

    override fun onDestroy() {
        serverThread?.stopThread()
        super.onDestroy()
    }


}


