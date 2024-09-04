package com.example.odin.ui.screens.center.screens.tools.tools_odin.control_memory

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

/**
 * ViewModel para controlar la memoria y realizar operaciones sobre ella.
 *
 * @property context Contexto de la aplicación para mostrar Toasts y acceder al servicio de portapapeles.
 */
@SuppressLint("StaticFieldLeak")
class ControlMemoryViewModel(private val context: Context) : ViewModel(), InterfaceControlMemory {

    /**
     * Estado de la UI que contiene información sobre el estado de la memoria y entradas del usuario.
     *
     * @property input Cadena de entrada.
     * @property inputCustom Entrada personalizada.
     * @property inputSearch Cadena de búsqueda.
     * @property size Tamaño de la memoria.
     * @property complete Número de elementos completados en la memoria.
     * @property marker Lista de claves que coinciden con la búsqueda.
     */
    data class UiState(
        val input: String = "",
        val inputCustom: String = "",
        val inputSearch: String = "",
        val size: Int = 32,
        var complete: Int = 0,
        val marker: MutableList<String> = mutableListOf()
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _matrixState = MutableStateFlow(initializeMatrix(_uiState.value.size))
    val matrixState = _matrixState.asStateFlow()

    /**
     * Inicializa la matriz con un tamaño dado.
     *
     * @param size Tamaño de la matriz.
     * @return Matriz inicializada.
     */
    private fun initializeMatrix(size: Int): MutableMap<Long, Pair<Char, String>> {
        val initialMatrix = mutableMapOf<Long, Pair<Char, String>>()
        repeat(size) {
            val pair = Pair(' ', "0")
            initialMatrix[System.identityHashCode(pair).toLong()] = pair
        }
        _uiState.value = _uiState.value.copy(marker = mutableListOf())
        return initialMatrix
    }

    /**
     * Actualiza la cadena de entrada y muestra un mensaje de error si el tamaño excede el límite.
     *
     * @param input Nueva cadena de entrada.
     */
    fun onInputChanged(input: String) {
        if (input.length < 32) {
            _uiState.value = _uiState.value.copy(input = input)
        } else {
            Toast.makeText(
                context,
                "El tamaño no puede ser mayor a 32 bits de memoria",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Actualiza la cadena de búsqueda y muestra un mensaje de error si el tamaño excede el límite.
     *
     * @param inputSearch Nueva cadena de búsqueda.
     */
    fun onInputSearchChanged(inputSearch: String) {
        if (inputSearch.length < 32) {
            _uiState.value = _uiState.value.copy(inputSearch = inputSearch)
        } else {
            Toast.makeText(
                context,
                "El tamaño no puede ser mayor a 32 bits de memoria",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Actualiza la entrada personalizada y muestra un mensaje de error si el tamaño excede el límite.
     *
     * @param inputCustom Nueva entrada personalizada.
     */
    fun onInputCustomChanged(inputCustom: String) {
        if (inputCustom.length < 32) {
            _uiState.value = _uiState.value.copy(inputCustom = inputCustom)
        } else {
            Toast.makeText(
                context,
                "El tamaño no puede ser mayor a 32 bits de memoria",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Actualiza el tamaño de la memoria y reinicia la matriz y el contador de elementos completados.
     *
     * @param size Nuevo tamaño de la memoria.
     */
    fun onSizeChanged(size: String) {
        val newSize = size.toIntOrNull() ?: 1
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

    /**
     * Reinicia la matriz con un nuevo tamaño.
     *
     * @param newSize Nuevo tamaño de la matriz.
     */
    private fun resetMatrix(newSize: Int) {
        _matrixState.value = initializeMatrix(newSize)
    }

    /**
     * Reinicia el contador de elementos completados.
     */
    private fun resetComplete() {
        _uiState.value = _uiState.value.copy(complete = 0)
    }

    /**
     * Resetea la matriz y el contador de elementos completados.
     */
    fun onClickChanged() {
        val currentSize = _uiState.value.size
        _uiState.value = _uiState.value.copy(complete = 0)
        val newMatrix = mutableMapOf<Long, Pair<Char, String>>()
        repeat(currentSize) {
            val pair = Pair(' ', "0")
            newMatrix[System.identityHashCode(pair).toLong()] = pair
        }
        updateMatrix(newMatrix)
    }

    /**
     * Copia un texto al portapapeles.
     *
     * @param text Texto a copiar.
     */
    fun copyToClipboard(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        Toast.makeText(context, "Copiado", Toast.LENGTH_SHORT).show()
        clipboard.setPrimaryClip(clip)
    }

    /**
     * Actualiza la matriz y el marcador en el estado de la UI.
     *
     * @param newMatrix Nueva matriz.
     */
    private fun updateMatrix(newMatrix: MutableMap<Long, Pair<Char, String>>) {
        _matrixState.value = newMatrix
        _uiState.value = _uiState.value.copy(marker = mutableListOf())
    }

    /**
     * Verifica si la matriz está vacía.
     *
     * @return `true` si la matriz está vacía, `false` en caso contrario.
     */
    private fun isEmpty(): Boolean = _matrixState.value.all { it.value.first == ' ' }

    /**
     * Busca una cadena en la matriz y actualiza el marcador con las claves que coinciden.
     *
     * @param cadena Cadena a buscar.
     */
    override fun get(cadena: String) {
        _uiState.value = _uiState.value.copy(marker = mutableListOf())
        if (isEmpty()) {
            Toast.makeText(context, "No hay elementos en la memoria", Toast.LENGTH_SHORT).show()
            return
        }

        if (cadena.isEmpty()) {
            Toast.makeText(context, "No se ha ingresado una cadena", Toast.LENGTH_SHORT).show()
            return
        }

        // Convertir cadena a Long para la comparación si es necesario
        val searchKey = cadena.toLongOrNull()
        if (searchKey == null) {
            Toast.makeText(
                context,
                "La cadena ingresada no es una dirección válida",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Crear una copia del marcador y añadir claves coincidentes
        val updatedMarker = _uiState.value.marker.toMutableList()
        val uids = _matrixState.value[searchKey]?.second ?: ""

        for ((keys, values) in _matrixState.value) {
            if (values.second != "0" && values.second == uids) {
                updatedMarker.add(keys.toString())
            }
        }

        // Actualizar el marcador en el estado
        _uiState.value = _uiState.value.copy(marker = updatedMarker)

        // Verificar si se encontraron coincidencias
        if (updatedMarker.isEmpty()) {
            Toast.makeText(context, "No se encontraron coincidencias", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Añade una cadena a la memoria.
     *
     * @param cadena Cadena a añadir.
     * @return `true` si la cadena se añadió correctamente, `false` en caso contrario.
     */
    override fun add(cadena: String): Boolean {
        _uiState.value = _uiState.value.copy(marker = mutableListOf())
        if ((_uiState.value.size - _uiState.value.complete) < cadena.length) {
            Toast.makeText(context, "No hay espacio suficiente", Toast.LENGTH_SHORT).show()
            return false
        }
        if (cadena.isEmpty()) {
            Toast.makeText(context, "La cadena está vacía", Toast.LENGTH_SHORT).show()
            return false
        }

        val uid = Random.nextInt(100000, 999999).toString() + "-" + cadena.replace(" ", "")
        val newMatrix = _matrixState.value.toMutableMap()
        var addedCount = 0
        val cadenaUpper = cadena.replace(" ", "")

        for ((key, value) in newMatrix) {
            if (value.first == ' ' && addedCount < cadenaUpper.length) {
                newMatrix[key] = Pair(cadenaUpper[addedCount], uid)
                addedCount++
            }
        }
        _uiState.value =
            _uiState.value.copy(complete = _uiState.value.complete + cadenaUpper.length)
        updateMatrix(newMatrix)
        return addedCount == cadenaUpper.length
    }

    /**
     * Borra todos los elementos de la memoria.
     *
     * @return `true` si la memoria se borró correctamente.
     */
    override fun clear(): Boolean {
        _uiState.value = _uiState.value.copy(marker = mutableListOf())
        resetMatrix(_uiState.value.size)
        _uiState.value = _uiState.value.copy(complete = 0)
        return true
    }

    /**
     * Elimina una cadena específica de la memoria.
     *
     * @param cadena Cadena a eliminar.
     * @return `true` si la cadena se eliminó correctamente, `false` en caso contrario.
     */
    override fun delete(cadena: String): Boolean {
        _uiState.value = _uiState.value.copy(marker = mutableListOf())
        val finalChain = cadena.replace(" ", "")
        val newMatrix = _matrixState.value.toMutableMap()
        var found = false
        for ((key, value) in newMatrix) {
            if (value.second != "0" && value.second.split("-")[1] == finalChain) {
                newMatrix[key] = ' ' to "0"
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

    /**
     * Elimina un elemento específico basado en su UID.
     *
     * @param uid UID del elemento a eliminar.
     * @return Un par donde el primer valor indica si el elemento fue encontrado y eliminado,
     *         y el segundo valor indica la cantidad de instancias eliminadas.
     */
    override fun deleteElement(uid: String): Pair<Boolean, String> {
        _uiState.value = _uiState.value.copy(marker = mutableListOf())
        val newMatrix = _matrixState.value.toMutableMap()
        var found = false
        var j = 0
        var count = 0
        for ((key, value) in newMatrix) {
            if (value.second != "0" && value.second.split("-")[0] == uid) {
                newMatrix[key] = ' ' to "0"
                _uiState.value = _uiState.value.copy(complete = _uiState.value.complete - 1)
                found = true
                j++
                if (uid.length > j) {
                    count++
                    j = 0
                }
            }
        }
        if (!found) {
            Toast.makeText(context, "No existe el elemento", Toast.LENGTH_SHORT).show()
        }
        updateMatrix(newMatrix)
        return Pair(found, (count / uid.length).toString())
    }

    /**
     * Elimina un elemento específico basado en su UID (auxiliar).
     *
     * @param uid UID del elemento a eliminar.
     * @return Un par donde el primer valor indica si el elemento fue encontrado y eliminado,
     *         y el segundo valor indica la cantidad de instancias eliminadas.
     */
    private fun deleteUid(uid: String): Pair<Boolean, String> {
        _uiState.value = _uiState.value.copy(marker = mutableListOf())
        val newMatrix = _matrixState.value.toMutableMap()
        var found = false
        var j = 0
        var count = 0
        for ((key, value) in newMatrix) {
            if (value.second != "0" && value.second.split("-")[1] == uid) {
                newMatrix[key] = ' ' to "0"
                _uiState.value = _uiState.value.copy(complete = _uiState.value.complete - 1)
                found = true
                j++
                if (uid.length > j) {
                    count++
                    j = 0
                }
            }
        }
        if (!found) {
            Toast.makeText(context, "No existe el elemento", Toast.LENGTH_SHORT).show()
        }
        updateMatrix(newMatrix)
        return Pair(found, (count / uid.length).toString())
    }

    /**
     * Reemplaza una cadena en la memoria por otra.
     *
     * @param cadena Cadena a reemplazar.
     * @param cadena2 Nueva cadena a añadir.
     * @return `true` si el reemplazo fue exitoso, `false` en caso contrario.
     */
    override fun replace(cadena: String, cadena2: String): Boolean {
        _uiState.value = _uiState.value.copy(marker = mutableListOf())
        val cadenaOriginal = cadena.replace(" ", "")
        val result = deleteUid(cadenaOriginal)
        val removedCount = result.second.toInt()
        if (!result.first) return false

        if ((_uiState.value.size - _uiState.value.complete) < cadena2.length * removedCount) {
            Toast.makeText(
                context,
                "No hay espacio suficiente para reemplazar todas las instancias",
                Toast.LENGTH_SHORT
            ).show()
            for (removed in 0 until removedCount) add(cadena)
            return false
        }

        if (removedCount == 0) add(cadena2)
        for (removed in 0 until removedCount) add(cadena2)

        return true
    }
}
