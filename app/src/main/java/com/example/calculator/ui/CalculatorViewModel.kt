package com.example.calculator.ui

import androidx.lifecycle.ViewModel
import com.example.calculator.data.CalculateType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculatorViewModel:ViewModel() {
    private val _inputNumberState:MutableStateFlow<Double?> = MutableStateFlow(null)
    val inputNumberState:StateFlow<Double?> = _inputNumberState.asStateFlow()

    private val _inputTextState:MutableStateFlow<String> = MutableStateFlow("0")
    val inputTextState:StateFlow<String> = _inputTextState.asStateFlow()

    private val _totalState:MutableStateFlow<Double?> = MutableStateFlow(null)
    val totalState:StateFlow<Double?> = _totalState.asStateFlow()

    private val _calculateType: MutableStateFlow<CalculateType?> = MutableStateFlow(null)

    private val _calculateTypeText: MutableStateFlow<String> = MutableStateFlow("")
    val calculateTypeText: StateFlow<String> = _calculateTypeText.asStateFlow()

    private val _decimalMode: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun inputNumber(num: Double) {
        if(_inputNumberState.value != null){
            if(_decimalMode.value) {
                val previousNumber: String = formatNumber(_inputNumberState.value!!)
                val newNumberText: String = formatNumber(num)
                val newNumber: String = previousNumber.plus(".$newNumberText")
                _inputNumberState.value = newNumber.toDouble()
                _inputTextState.value = newNumber
                _decimalMode.value = false
            } else {
                val newNumberText: String = formatNumber(num)
                val newNumber = _inputTextState.value.plus(newNumberText)
                _inputNumberState.value = newNumber.toDouble()
                _inputTextState.value = newNumber
            }
        } else {
            _inputNumberState.value = num
            _inputTextState.value = formatNumber(num)
        }
    }

    fun plusNumber() {
        calculateNumber(CalculateType.PLUS)
    }

    fun minusNumber() {
        calculateNumber(CalculateType.MINUS)
    }

    fun multiplyNumber() {
        calculateNumber(CalculateType.MULTIPLY)
    }

    fun divideNumber() {
        calculateNumber(CalculateType.DIVIDE)
    }

    private fun calculateNumber(
        thisCalculateType: CalculateType
    ) {
        val inputValue = _inputNumberState.value
        val totalValue = _totalState.value

        if(inputValue != null) {
            if(_calculateType.value != null && totalValue != null) {
                when(_calculateType.value) {
                    CalculateType.PLUS -> _totalState.value = totalValue.plus(inputValue)
                    CalculateType.MINUS -> _totalState.value = totalValue.minus(inputValue)
                    CalculateType.MULTIPLY -> _totalState.value = totalValue.times(inputValue)
                    CalculateType.DIVIDE -> _totalState.value = totalValue.div(inputValue)
                    else -> return
                }
            } else {
                _totalState.value = inputValue
            }
            _calculateType.value = thisCalculateType
            _calculateTypeText.value = when(thisCalculateType) {
                CalculateType.PLUS -> "+"
                CalculateType.MINUS -> "-"
                CalculateType.MULTIPLY -> "×"
                CalculateType.DIVIDE -> "÷"
            }
            _inputTextState.value = formatNumber(_totalState.value!!)
            _inputNumberState.value = null
            _decimalMode.value = false
        }
    }

    fun clearCalculator() {
        _inputNumberState.value = null
        _totalState.value = null
        _calculateType.value = null
        _calculateTypeText.value = ""
        _decimalMode.value = false
        _inputTextState.value = "0"
    }

    fun sumUpCalculate() {
        val inputValue = _inputNumberState.value
        val totalValue = _totalState.value

        if(inputValue != null && _calculateType.value != null) {
            if(totalValue != null) {
                when(_calculateType.value) {
                    CalculateType.PLUS -> _totalState.value = totalValue.plus(inputValue)
                    CalculateType.MINUS -> _totalState.value = totalValue.minus(inputValue)
                    CalculateType.MULTIPLY -> _totalState.value = totalValue.times(inputValue)
                    CalculateType.DIVIDE -> _totalState.value = totalValue.div(inputValue)
                    else -> return
                }
            }
            _calculateType.value = null
            _inputNumberState.value = _totalState.value
            _inputTextState.value = formatNumber(_totalState.value!!)
            _calculateTypeText.value = ""
            _decimalMode.value = false
        }
    }

    fun formatNumber(number: Double): String {
        val srtNumber = number.toString()
        return if(srtNumber.endsWith(".0")) {
            srtNumber.substring(0, srtNumber.length - 2)
        } else {
            return srtNumber
        }
    }

    fun changeToDecimal() {
        if(_inputNumberState.value != null) {
            val inputNumberText = _inputNumberState.value.toString()
            _decimalMode.value = inputNumberText.endsWith(".0")
            if(_decimalMode.value) {
                val number = formatNumber(_inputNumberState.value!!)
                _inputTextState.value = number.plus(".")
            }
        } else {
            _inputNumberState.value = 0.0
            val number = formatNumber(_inputNumberState.value!!)
            _inputTextState.value = number.plus(".")
            _decimalMode.value = true
        }
    }
}