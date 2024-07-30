package com.example.calculator.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorHomeScreen(
    windowSize: WindowWidthSizeClass,
    viewModel: CalculatorViewModel = CalculatorViewModel()
) {
    val inputTextValue by viewModel.inputTextState.collectAsState()
    val calculateTypeText by viewModel.calculateTypeText.collectAsState()
    val calculateColor = 0xFF001A74
    val topSpace: Float = when(windowSize) {
        WindowWidthSizeClass.Compact -> 0.5F
        WindowWidthSizeClass.Medium -> 0.4F
        WindowWidthSizeClass.Expanded -> 0.01F
        else -> 0.5F
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("デンタク") }
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.weight(topSpace))
            Row(
                modifier = Modifier.padding(start = 4.dp)
            ){
                Box(
                    modifier = Modifier.clip(CircleShape).background(Color(0xFF001A74))
                ) {
                    Text(
                        calculateTypeText,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                }
                Text(
                    inputTextValue,
                    fontSize = 50.sp,
                    lineHeight = 1.em,
                    modifier = Modifier.padding(16.dp)
                )
            }
            if(windowSize == WindowWidthSizeClass.Compact || windowSize == WindowWidthSizeClass.Medium) {
                PhoneLayout(
                    viewModel = viewModel,
                    calculateColor = calculateColor,
                    modifier = Modifier.weight(1F)
                )
            } else if(windowSize == WindowWidthSizeClass.Expanded) {
                PcLayout(
                    viewModel = viewModel,
                    calculateColor = calculateColor,
                    modifier = Modifier.weight(1F)
                )
            }
        }
    }
}


@Composable
fun ContentButton(
    text: String,
    onClick: () -> Unit,
    textColor: Color = Color.White,
    containerColor: Color = Color.DarkGray,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize(),
        colors =  ButtonDefaults.buttonColors(
            containerColor = containerColor
        )
    ) {
        Text(
            text,
            fontSize = 28.sp,
            color = textColor
        )
    }
}

@Composable
fun PhoneLayout(
    viewModel: CalculatorViewModel,
    calculateColor: Long,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.weight(1f)) {
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(7.0)
                },
                text = "7"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(8.0)
                },
                text = "8"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(9.0)
                },
                text = "9"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.divideNumber() },
                containerColor = Color(calculateColor),
                text = "÷"
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(4.0)
                },
                text = "4"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(5.0)
                },
                text = "5"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(6.0)
                },
                text = "6"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.multiplyNumber() },
                containerColor = Color(calculateColor),
                text = "×"
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(1.0)
                },
                text = "1"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(2.0)
                },
                text = "2"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(3.0)
                },
                text = "3"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.minusNumber() },
                containerColor = Color(calculateColor),
                text = "-"
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            ContentButton(
                modifier = Modifier.weight(2f),
                onClick = {
                    viewModel.inputNumber(0.0)
                },
                text = "0"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.changeToDecimal()
                },
                text = "."
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.plusNumber() },
                containerColor = Color(calculateColor),
                text = "+"
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            ContentButton(
                modifier = Modifier.weight(3f),
                onClick = { viewModel.clearCalculator() },
                textColor = Color(0xFF444444),
                containerColor = Color(0xFFFFC825),
                text = "C"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.sumUpCalculate() },
                containerColor = Color(0xFF168000),
                text = "="
            )
        }
    }
}

@Composable
fun PcLayout(
    viewModel: CalculatorViewModel,
    calculateColor: Long,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.weight(1f)) {
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(5.0)
                },
                text = "5"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(6.0)
                },
                text = "6"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(7.0)
                },
                text = "7"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(8.0)
                },
                text = "8"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(9.0)
                },
                text = "9"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.minusNumber() },
                containerColor = Color(calculateColor),
                text = "-"
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(0.0)
                },
                text = "0"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(1.0)
                },
                text = "1"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(2.0)
                },
                text = "2"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(3.0)
                },
                text = "3"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.inputNumber(4.0)
                },
                text = "4"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.plusNumber() },
                containerColor = Color(calculateColor),
                text = "+"
            )
        }

        Row(modifier = Modifier.weight(1f)) {
            ContentButton(
                modifier = Modifier.weight(2f),
                onClick = { viewModel.clearCalculator() },
                textColor = Color(0xFF444444),
                containerColor = Color(0xFFFFC825),
                text = "C"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    viewModel.changeToDecimal()
                },
                text = "."
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.divideNumber() },
                containerColor = Color(calculateColor),
                text = "÷"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.multiplyNumber() },
                containerColor = Color(calculateColor),
                text = "×"
            )
            ContentButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.sumUpCalculate() },
                containerColor = Color(0xFF168000),
                text = "="
            )
        }

    }
}