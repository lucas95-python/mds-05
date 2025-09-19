package com.cursodedesenvolvimentodesoftware

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursodedesenvolvimentodesoftware.ui.theme.IMCTheme
import com.cursodedesenvolvimentodesoftware.ui.theme.Primary
import com.cursodedesenvolvimentodesoftware.ui.theme.views.ImcCalculator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMCTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            16.dp,
                            24.dp
                        ),
                    topBar = {
                        Text(
                            text = "IMC Calculator",
                            color = Primary,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }) { innerPadding ->
                    ImcCalculator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}