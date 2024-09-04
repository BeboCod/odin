package com.example.odin.ui.screens.center.screens.tools.tools_odin.control_memory

/**
 * Interfaz para el control de operaciones en una memoria.
 * Define las operaciones básicas que se pueden realizar en la memoria.
 */
interface InterfaceControlMemory {

    /**
     * Obtiene elementos de la memoria basados en una cadena de búsqueda.
     *
     * @param cadena Cadena que se usará para la búsqueda.
     */
    fun get(cadena: String)

    /**
     * Añade una cadena a la memoria.
     *
     * @param cadena Cadena que se añadirá a la memoria.
     * @return `true` si la cadena se añadió correctamente, `false` en caso contrario.
     */
    fun add(cadena: String): Boolean

    /**
     * Borra todos los elementos de la memoria.
     *
     * @return `true` si la memoria se borró correctamente.
     */
    fun clear(): Boolean

    /**
     * Elimina una cadena específica de la memoria.
     *
     * @param cadena Cadena a eliminar.
     * @return `true` si la cadena se eliminó correctamente, `false` en caso contrario.
     */
    fun delete(cadena: String): Boolean

    /**
     * Elimina un elemento específico basado en su UID.
     *
     * @param uid UID del elemento a eliminar.
     * @return Un par donde el primer valor indica si el elemento fue encontrado y eliminado,
     *         y el segundo valor indica la cantidad de instancias eliminadas.
     */
    fun deleteElement(uid: String): Pair<Boolean, String>

    /**
     * Reemplaza una cadena en la memoria por otra.
     *
     * @param cadena Cadena a reemplazar.
     * @param cadena2 Nueva cadena a añadir.
     * @return `true` si el reemplazo fue exitoso, `false` en caso contrario.
     */
    fun replace(cadena: String, cadena2: String): Boolean
}
