package ro.pub.cs.systems.eim.lab06.clientservercommunication.network

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Constants
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Utilities
import java.io.IOException
import java.net.Socket
import kotlin.concurrent.thread

class ClientAsyncTask(private val serverMessageTextView: TextView) {

    // posts code to the UI thread (only the UI thread may change views)
    private val mainHandler = Handler(Looper.getMainLooper())

    fun execute(serverAddress: String, serverPort: String) {
        // exercise 5b
        serverMessageTextView.text = ""

        thread {
            var socket: Socket? = null
            try {
                socket = Socket(serverAddress, serverPort.toInt())
                Log.v(Constants.TAG, "Connection opened with ${socket.inetAddress}:${socket.localPort}")
                val bufferedReader = Utilities.getReader(socket)
                var line = bufferedReader.readLine()
                while (line != null) {
                    val currentLine = line
                    mainHandler.post { serverMessageTextView.append(currentLine + "\n") }
                    line = bufferedReader.readLine()
                }
            } catch (ioException: IOException) {
                Log.e(Constants.TAG, "An exception has occurred: ${ioException.message}")
            } finally {
                try {
                    socket?.close()
                    Log.v(Constants.TAG, "Connection closed")
                } catch (ioException: IOException) {
                    Log.e(Constants.TAG, "An exception has occurred: ${ioException.message}")
                }
            }
        }
    }
}
