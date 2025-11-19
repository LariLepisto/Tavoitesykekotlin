package com.example.tavoitesykekotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.padding(20.dp)) {
                    HeartRateScreen()
                }
            }
        }
    }
}

@Composable
fun HeartRateScreen() {
    var age by remember { mutableStateOf("") }

    val ageNumber = age.toIntOrNull()
    val maxHr = ageNumber?.let { 220 - it }
    val lower = maxHr?.let { (it * 0.5).toInt() }
    val upper = maxHr?.let { (it * 0.85).toInt() }

    Column {
        Text("Heart rate limits", fontSize = 26.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (ageNumber == null) {
            Text("Enter age to calculate limits")
        } else {
            Text("Maximum heart rate: $maxHr bpm")
            Text("Lower limit: $lower bpm")
            Text("Upper limit: $upper bpm")
        }
    }
}