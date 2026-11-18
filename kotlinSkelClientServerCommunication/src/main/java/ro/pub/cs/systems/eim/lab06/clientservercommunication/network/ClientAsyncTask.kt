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
        // TODO: exercise 5b
        // 1. clear serverMessageTextView (execute() is called on the UI thread)
        // 2. start a new thread (thread { ... }) and, on it:
        //    - open a Socket to serverAddress / serverPort.toInt()
        //    - get a BufferedReader with Utilities.getReader(socket)
        //    - read lines until readLine() returns null; append each line + "\n"
        //      to serverMessageTextView on the UI thread (mainHandler.post { ... })
        //    - catch IOException and log it (Log.e(Constants.TAG, ...))
        //    - close the socket in a finally block
    }
}
