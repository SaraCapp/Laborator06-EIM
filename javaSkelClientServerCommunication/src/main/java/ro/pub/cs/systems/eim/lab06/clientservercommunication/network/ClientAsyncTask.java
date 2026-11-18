package ro.pub.cs.systems.eim.lab06.clientservercommunication.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Constants;
import ro.pub.cs.systems.eim.lab06.clientservercommunication.general.Utilities;

public class ClientAsyncTask {

    private final TextView serverMessageTextView;
    // posts code to the UI thread (only the UI thread may change views)
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public ClientAsyncTask(TextView serverMessageTextView) {
        this.serverMessageTextView = serverMessageTextView;
    }

    public void execute(String serverAddress, String serverPort) {
        // TODO: exercise 5b
        // 1. clear serverMessageTextView (execute() is called on the UI thread)
        // 2. start a new thread (new Thread(() -> { ... }).start()) and, on it:
        //    - open a Socket to serverAddress / Integer.parseInt(serverPort)
        //    - get a BufferedReader with Utilities.getReader(socket)
        //    - read lines until readLine() returns null; append each line + "\n"
        //      to serverMessageTextView on the UI thread (mainHandler.post(() -> ...))
        //    - catch IOException and log it (Log.e(Constants.TAG, ...))
        //    - close the socket in a finally block
    }
}
