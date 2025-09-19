package com.example.contador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.teste.manager.CounterDataStoreManager
import com.example.contador.ui.theme.ContadorTheme
import com.example.teste.view.CounterViewModel
import com.example.teste.view.CounterViewModelFactory
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.contador.ui.theme.ContadorTheme


class MainActivity : ComponentActivity() {

    private val dataStoreManager by lazy { CounterDataStoreManager(this) }

    private val counterViewModel: CounterViewModel by viewModels {
        CounterViewModelFactory(dataStoreManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContadorTheme {
                val clickCount by counterViewModel.clickCount.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize().padding(50.dp)) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        onClick = { counterViewModel.incrementCounter() },
                        count = clickCount
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick : () -> Unit, count: Int) {
    Column {
        Text(
            text = "$count!",
            modifier = modifier
        )
        Button(onClick=onClick) {
            Text("Clique")
        }
    }

}