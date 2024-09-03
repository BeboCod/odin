package com.example.odin.ui.screens.center.screens.tools.ToolsOdin.ControlMemory

interface InterfaceControlMemory {
    fun get(cadena: String): MutableList<Pair<Char, String>>
    fun add(cadena: String): Boolean
    fun clear(): Boolean
    fun delete(cadena: String): Boolean
    fun deleteElement(ascii: String): Pair<Boolean, String>
    fun replace(cadena: String, cadena2: String): Boolean
}