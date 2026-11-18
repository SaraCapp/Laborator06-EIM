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
        // exercise 5b
        serverMessageTextView.setText("");

        new Thread(() -> {
            Socket socket = null;
            try {
                socket = new Socket(serverAddress, Integer.parseInt(serverPort));
                Log.v(Constants.TAG, "Connection opened with " + socket.getInetAddress() + ":" + socket.getLocalPort());
                BufferedReader bufferedReader = Utilities.getReader(socket);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    final String currentLine = line;
                    mainHandler.post(() -> serverMessageTextView.append(currentLine + "\n"));
                }
            } catch (IOException ioException) {
                Log.e(Constants.TAG, "An exception has occurred: " + ioException.getMessage());
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                    Log.v(Constants.TAG, "Connection closed");
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "An exception has occurred: " + ioException.getMessage());
                }
            }
        }).start();
    }
}
