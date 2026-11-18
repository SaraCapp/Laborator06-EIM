package ro.pub.cs.systems.eim.lab06.singlethreadedserver.network

import android.util.Log
import android.widget.EditText
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Constants
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Utilities
import java.io.IOException
import java.net.ServerSocket

class ServerThread(private val serverTextEditText: EditText) : Thread() {

    private var isRunning = false

    private var serverSocket: ServerSocket? = null

    fun startServer() {
        isRunning = true
        start()
        Log.v(Constants.TAG, "startServer() method invoked $serverSocket")
    }

    fun stopServer() {
        isRunning = false
        try {
            serverSocket?.close()
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: ${ioException.message}")
            if (Constants.DEBUG) {
                ioException.printStackTrace()
            }
        }
        Log.v(Constants.TAG, "stopServer() method invoked")
    }

    override fun run() {
        try {
            val serverSocket = ServerSocket(Constants.SERVER_PORT)
            this.serverSocket = serverSocket
            while (isRunning) {
                val socket = serverSocket.accept()
                Log.v(Constants.TAG, "Connection opened with ${socket.inetAddress}:${socket.localPort}")

                // TODO: exercise 4c - simulate a slow client: wait 3 seconds (Thread.sleep(3000)) before answering

                val printWriter = Utilities.getWriter(socket)
                printWriter.println(serverTextEditText.text.toString())
                socket.close()
                Log.v(Constants.TAG, "Connection closed")

                // TODO: exercise 4d - move the communication with this client (sleep, write, close)
                // to a separate thread (one CommunicationThread per client)

            }
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: ${ioException.message}")
            if (Constants.DEBUG) {
                ioException.printStackTrace()
            }
        }
    }
}
