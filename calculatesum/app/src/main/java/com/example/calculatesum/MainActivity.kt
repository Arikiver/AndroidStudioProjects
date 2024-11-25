package com.example.calculatesum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatesum.ui.theme.SimpleAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SimpleForm()
                }
            }
        }
    }
}

@Composable
fun SimpleForm() {
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(text = "Calculation", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = input1,
            onValueChange = { input1 = it },
            label = { Text("Input 1") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = input2,
            onValueChange = { input2 = it },
            label = { Text("Input 2") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            val num1 = input1.toDoubleOrNull() ?: 0.0
            val num2 = input2.toDoubleOrNull() ?: 0.0
            result = "Result: ${num1 + num2}"
        }) {
            Text(text = "Add")
        }

        if (result.isNotEmpty()) {
            Text(text = result, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleFormPreview() {
    SimpleAppTheme {
        SimpleForm()
    }
}