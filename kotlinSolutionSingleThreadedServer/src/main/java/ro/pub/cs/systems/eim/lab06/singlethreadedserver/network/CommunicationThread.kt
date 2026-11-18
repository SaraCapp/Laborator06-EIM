package ro.pub.cs.systems.eim.lab06.singlethreadedserver.network

import android.util.Log
import android.widget.EditText
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Constants
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Utilities
import java.io.IOException
import java.net.Socket

class CommunicationThread(
    private val socket: Socket,
    private val serverTextEditText: EditText
) : Thread() {

    override fun run() {
        try {
            // exercise 4c
            try {
                Thread.sleep(3000)
            } catch (interruptedException: InterruptedException) {
                Log.e(Constants.TAG, "An exception has occurred: ${interruptedException.message}")
            }

            val printWriter = Utilities.getWriter(socket)
            printWriter.println(serverTextEditText.text.toString())
            socket.close()
            Log.v(Constants.TAG, "Connection closed")
        } catch (ioException: IOException) {
            Log.e(Constants.TAG, "An exception has occurred: ${ioException.message}")
            if (Constants.DEBUG) {
                ioException.printStackTrace()
            }
        }
    }
}
