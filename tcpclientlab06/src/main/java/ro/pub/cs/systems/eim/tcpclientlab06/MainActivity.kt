package ro.pub.cs.systems.eim.tcpclientlab06

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.net.InetSocketAddress
import java.net.Socket

class MainActivity : AppCompatActivity() {

    private lateinit var messageTextView: TextView

    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Gasim componentele din interfata
        val connectButton = findViewById<Button>(R.id.connectButton)
        messageTextView = findViewById(R.id.messageTextView)

        // Cand apasam pe buton, ne conectam la server
        connectButton.setOnClickListener {

            messageTextView.text = "Se conecteaza..."

            // Rulam comunicarea pe un thread separat
            Thread {
                try {
                    // 10.0.2.2 este adresa calculatorului din emulator
                    Socket().use { socket ->

                        // Conectare la server pe portul 2000
                        socket.connect(
                            InetSocketAddress("10.0.2.2", 2000),
                            5000
                        )

                        // Citim mesajul primit de la server
                        val reader = socket.getInputStream()
                            .bufferedReader()

                        val mesaj = reader.readLine() ?: ""

                        // Actualizam interfata pe thread-ul principal
                        mainHandler.post {
                            messageTextView.text = mesaj
                        }
                    }

                } catch (e: Exception) {

                    // Afisam eroarea daca nu merge conexiunea
                    mainHandler.post {
                        messageTextView.text =
                            "Eroare: ${e.message}"
                    }
                }
            }.start()
        }
    }
}
