package com.example.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CurrencyConverterScreen()
            }
        }
    }
}

@Composable
fun CurrencyConverterScreen() {
    var rupiahInput by remember { mutableStateOf("") }
    var dolarInput by remember { mutableStateOf("") }

    val kurs = 15000.0 // Kurs statis: 1 USD = 15.000 IDR

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Konversi Mata Uang",
            style = TextStyle(fontSize = 24.sp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Input Rupiah
        TextField(
            value = rupiahInput,
            onValueChange = { newText ->
                rupiahInput = newText
                // Konversi otomatis ke Dolar
                val rupiahValue = newText.toDoubleOrNull()
                dolarInput = if (rupiahValue != null) {
                    (rupiahValue / kurs).toString()
                } else {
                    ""
                }
            },
            label = { Text("Rupiah (IDR)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Dolar
        TextField(
            value = dolarInput,
            onValueChange = { newText ->
                dolarInput = newText
                // Konversi otomatis ke Rupiah
                val dolarValue = newText.toDoubleOrNull()
                rupiahInput = if (dolarValue != null) {
                    (dolarValue * kurs).toString()
                } else {
                    ""
                }
            },
            label = { Text("Dolar (USD)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Reset
        Button(onClick = {
            rupiahInput = ""
            dolarInput = ""
        }) {
            Text("Reset")
        }
    }
}