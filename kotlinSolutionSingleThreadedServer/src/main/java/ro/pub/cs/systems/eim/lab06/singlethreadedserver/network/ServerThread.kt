package ro.pub.cs.systems.eim.lab06.singlethreadedserver.network

import android.util.Log
import android.widget.EditText
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Constants
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

                // exercise 4d
                val communicationThread = CommunicationThread(socket, serverTextEditText)
                communicationThread.start()
            }
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: ${ioException.message}")
            if (Constants.DEBUG) {
                ioException.printStackTrace()
            }
        }
    }
}
