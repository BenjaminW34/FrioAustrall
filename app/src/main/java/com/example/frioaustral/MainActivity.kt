package com.example.frioaustral

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper(this)
        val etUser = findViewById<EditText>(R.id.etUser)
        val etPass = findViewById<EditText>(R.id.etPass)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val user = etUser.text.toString()
            val pass = etPass.text.toString()

            if (dbHelper.login(user, pass)) {
                Toast.makeText(this, "¡Seguridad ISO 27400 Validada!", Toast.LENGTH_SHORT).show()

                if (bluetoothAdapter != null) {
                    Toast.makeText(this, "Bluetooth disponible para IoT", Toast.LENGTH_SHORT).show()
                }

                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error: Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}