package com.cursodedesenvolvimentodesoftware.imc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable principal que representa a tela da Calculadora de Índice de Massa Corporal (IMC).
 * Permite ao usuário inserir peso e altura, calcula o IMC e exibe o resultado
 * juntamente com uma classificação e uma cor indicativa.
 */
@Composable
fun IMCCalculatorApp() {

    // Estado para armazenar o valor do campo de texto do peso.
    var pesoInput by remember { mutableStateOf("") }
    // Estado para armazenar o valor do campo de texto da altura.
    var alturaInput by remember { mutableStateOf("") }
    // Estado para armazenar o resultado do cálculo do IMC (pode ser nulo se a entrada for inválida).
    var imcResult by remember { mutableStateOf<Double?>(null) }
    // Estado para armazenar o texto da classificação do IMC ou mensagens de erro.
    var resultadoTexto by remember { mutableStateOf("") }
    // Gerenciador de foco para controlar o teclado virtual.
    val focusManager = LocalFocusManager.current

    /**
     * Calcula o Índice de Massa Corporal (IMC).
     *
     * @param peso O peso da pessoa em quilogramas (kg).
     * @param alturaCm A altura da pessoa em centímetros (cm).
     * @return O valor do IMC calculado.
     */
    fun calcularIMC(
        peso: Double,
        alturaCm: Double
    ): Double {
        val alturaM = alturaCm / 100 // Converte altura de cm para metros.
        return peso / (alturaM * alturaM) // Fórmula do IMC: peso / (altura_em_metros)^2.
    }

    /**
     * Classifica o IMC de acordo com as faixas padrão.
     *
     * @param imc O valor do IMC a ser classificado.
     * @return Uma string descrevendo a classificação do IMC (e.g., "Peso normal", "Sobrepeso").
     */
    fun classificarIMC(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 24.9 -> "Peso normal"
            imc < 29.9 -> "Sobrepeso"
            else -> "Obesidade"
        }
    }

    /**
     * Determina a cor a ser usada para exibir o resultado do IMC,
     * baseando-se na sua classificação.
     *
     * @param imc O valor do IMC.
     * @return Um objeto [Color] correspondente à faixa do IMC.
     */
    fun corPorIMC(imc: Double): Color {
        return when {
            imc < 18.5 -> Color(0xFF2196F3) // Azul para "Abaixo do peso"
            imc < 24.9 -> Color(0xFF4CAF50) // Verde para "Peso normal"
            imc < 29.9 -> Color(0xFFFFA000) // Laranja para "Sobrepeso"
            else -> Color(0xFFD32F2F) // Vermelho para "Obesidade"
        }
    }

    // Layout principal da tela, organiza os componentes verticalmente.
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo o espaço disponível.
            .padding(24.dp), // Adiciona preenchimento nas bordas da tela.
        horizontalAlignment = Alignment.CenterHorizontally, // Centraliza os componentes horizontalmente.
        verticalArrangement = Arrangement.Top // Alinha os componentes ao topo.
    )
    {
        // Título da aplicação.
        Text(
            text = "Calculadora de IMC",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp),
            color = MaterialTheme.colorScheme.primary // Usa a cor primária do tema.
        )

        // Campo de texto para inserção do peso.
        OutlinedTextField(
            value = pesoInput,
            onValueChange = {
                pesoInput = it.filter { char -> char.isDigit() || char == '.' }
            }, // Permite apenas dígitos e um ponto decimal.
            label = { Text("Peso (kg)") },
            singleLine = true, // Permite apenas uma linha de texto.
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Define o teclado para numérico.
            modifier = Modifier.fillMaxWidth() // Ocupa toda a largura disponível.
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espaçador vertical.

        // Campo de texto para inserção da altura.
        OutlinedTextField(
            value = alturaInput,
            onValueChange = {
                alturaInput = it.filter { char -> char.isDigit() || char == '.' }
            }, // Permite apenas dígitos e um ponto decimal.
            label = { Text("Altura (cm)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botão para acionar o cálculo do IMC.
        Button(
            onClick = {
                focusManager.clearFocus() // Remove o foco dos campos de texto, escondendo o teclado.
                val peso =
                    pesoInput.toDoubleOrNull() // Tenta converter a entrada de peso para Double.
                val altura =
                    alturaInput.toDoubleOrNull() // Tenta converter a entrada de altura para Double.

                // Verifica se os valores de peso e altura são válidos.
                if (peso != null && altura != null && peso > 0 && altura > 0) {
                    val imc = calcularIMC(
                        peso,
                        altura
                    )
                    imcResult = imc // Armazena o resultado do IMC.
                    resultadoTexto = classificarIMC(imc) // Armazena a classificação do IMC.
                } else {
                    imcResult = null // Reseta o resultado do IMC.
                    resultadoTexto =
                        "Por favor, insira valores válidos." // Define mensagem de erro.
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp), // Define altura fixa para o botão.
        ) {
            Text(
                text = "Calcular IMC",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Seção para exibir o resultado do IMC.
        if (imcResult != null) { // Exibe apenas se houver um resultado válido.
            Text(
                text = "Seu IMC é: ${"%.2f".format(imcResult)}", // Formata o IMC para duas casas decimais.
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = corPorIMC(imcResult!!) // Define a cor do texto baseada no IMC.
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = resultadoTexto, // Exibe a classificação do IMC.
                fontSize = 20.sp,
                color = corPorIMC(imcResult!!) // Define a cor do texto baseada no IMC.
            )
        } else if (resultadoTexto.isNotEmpty()) { // Exibe mensagem de erro se o resultado for nulo mas houver texto de resultado.
            Text(
                text = resultadoTexto,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Red // Cor vermelha para mensagens de erro.
            )
        }
    }
}