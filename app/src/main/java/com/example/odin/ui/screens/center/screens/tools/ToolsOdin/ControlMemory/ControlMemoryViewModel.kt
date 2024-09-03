package com.example.odin.ui.screens.center.screens.tools.ToolsOdin.ControlMemory

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ControlMemoryViewModel(private val context: Context) : ViewModel(), InterfaceControlMemory {

    data class UiState(
        val input: String = "",
        val inputCustom: String = "",
        val size: Int = 32,
        var complete: Int = 0
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _matrixState = MutableStateFlow(MutableList(_uiState.value.size) { Pair(' ', "0") })
    val matrixState = _matrixState.asStateFlow()

    fun onInputChanged(input: String) {
        _uiState.value = _uiState.value.copy(input = input)
    }

    fun onInputCustomChanged(inputCustom: String) {
        _uiState.value = _uiState.value.copy(inputCustom = inputCustom)
    }

    fun onSizeChanged(size: String) {
        val newSize = if (size.isNotEmpty()) size.toInt() else 0
        if (newSize < 0) {
            Toast.makeText(context, "El tamaño no puede ser negativo", Toast.LENGTH_SHORT).show()
            return
        }
        if (newSize > 999) {
            Toast.makeText(context, "El tamaño no puede ser mayor a 999", Toast.LENGTH_SHORT).show()
            return
        }
        if (newSize == _uiState.value.size) return
        _uiState.value = _uiState.value.copy(size = newSize)
        resetMatrix(newSize)
        resetComplete()
    }

    private fun resetMatrix(newSize: Int) {
        _matrixState.value = MutableList(newSize) { Pair(' ', "0") }
    }

    private fun resetComplete() {
        _uiState.value = _uiState.value.copy(complete = 0)
    }

    fun onClickChanged() {
        val currentSize = _uiState.value.size
        _uiState.value = _uiState.value.copy(complete = 0)
        val newMatrix = MutableList(currentSize) { Pair(' ', "0") }
        updateMatrix(newMatrix)
    }

    private fun updateMatrix(newMatrix: MutableList<Pair<Char, String>>) {
        _matrixState.value = newMatrix
    }

    private fun isEmpty(): Boolean = _matrixState.value.all { it.first == ' ' }

    private fun asciiCode(cadena: String): String = cadena.map { it.code.toString()[0] }.joinToString("")

    override fun get(cadena: String): MutableList<Pair<Char, String>> {
        if (isEmpty()) return mutableListOf()
        val ascii = asciiCode(cadena)
        return _matrixState.value.filter { it.second == ascii }.toMutableList()
    }

    override fun add(cadena: String): Boolean {
        if ((_uiState.value.size - _uiState.value.complete) < cadena.length) {
            Toast.makeText(context, "No hay espacio suficiente", Toast.LENGTH_SHORT).show()
            return false
        }
        if (cadena.isEmpty()) {
            Toast.makeText(context, "La cadena esta vacia", Toast.LENGTH_SHORT).show()
            return false
        }
        val ascii = asciiCode(cadena.replace(" ", ""))
        val newMatrix = _matrixState.value.toMutableList()
        var addedCount = 0
        val cadenaUpper = cadena.replace(" ", "")

        for (i in newMatrix.indices) {
            if (newMatrix[i].first == ' ' && addedCount < cadenaUpper.length) {
                newMatrix[i] = Pair(cadenaUpper[addedCount], ascii)
                addedCount++
            }
        }
        _uiState.value = _uiState.value.copy(complete = _uiState.value.complete + cadenaUpper.length)
        updateMatrix(newMatrix)
        return addedCount == cadenaUpper.length
    }

    override fun clear(): Boolean {
        resetMatrix(_uiState.value.size)
        _uiState.value = _uiState.value.copy(complete = 0)
        return true
    }

    override fun delete(cadena: String): Boolean {
        val ascii = asciiCode(cadena.replace(" ", ""))
        val newMatrix = _matrixState.value.toMutableList()
        var found = false
        for (i in newMatrix.indices) {
            if (newMatrix[i].second == ascii) {
                newMatrix[i] = ' ' to "0"
                _uiState.value = _uiState.value.copy(complete = _uiState.value.complete - 1)
                found = true
            }
        }
        if (!found) {
            Toast.makeText(context, "No existe el elemento", Toast.LENGTH_SHORT).show()
        }
        updateMatrix(newMatrix)
        return found
    }

    override fun deleteElement(ascii: String): Pair<Boolean, String> {
        val newMatrix = _matrixState.value.toMutableList()
        var found = false
        var j = 0
        var count = 0
        for (i in newMatrix.indices) {
            if (newMatrix[i].second == ascii) {
                newMatrix[i] = ' ' to "0"
                _uiState.value = _uiState.value.copy(complete = _uiState.value.complete - 1)
                found = true
                j++
                if (ascii.length > j){
                    count++
                    j = 0
                }
            }
        }
        if (!found) {
            Toast.makeText(context, "No existe el elemento", Toast.LENGTH_SHORT).show()
        }
        updateMatrix(newMatrix)
        return Pair(found, (count/ascii.length).toString())
    }

    override fun replace(cadena: String, cadena2: String): Boolean {
        val asciiOriginal = asciiCode(if (cadena != "") cadena.replace(" ", "") else return false)
        val result = deleteElement(asciiOriginal)
        val removedCount = result.second.toInt()
        if (!result.first) return false

        if ((_uiState.value.size - _uiState.value.complete) < cadena2.length * removedCount) {
            Toast.makeText(context, "No hay espacio suficiente para reemplazar todas las instancias", Toast.LENGTH_SHORT).show()
            for (removed in 0 until removedCount) add(cadena)
            return false
        }

        if (removedCount == 0) add(cadena2)
        for (removed in 0 until removedCount) add(cadena2)

        return true
    }

}
