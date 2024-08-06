package com.example.odin.ui.screens.center.screens.post

import androidx.lifecycle.ViewModel
import com.example.odin.data.service.Post
import com.example.odin.data.service.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostViewModel : ViewModel() {
    data class UiState(
        val isLiked: Boolean = false,
        val isSheetOpen: Boolean = false,
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun getData(id: String): Post = Post(//Agregar logica back
        id = id,
        title = "Programacion Orientada a Objetos (POO)",
        user = User(
            userName = "Override",
            urlImage = "https://www.shutterstock.com/image-vector/blank-avatar-photo-place-holder-600nw-1095249842.jpg",
            id = "51561563",
            verified = true
        ),
        description = """
            ¡Hola a todos los constructores de mundos y
            futuros programadores! En este artículo, vamos a
            sumergirnos en el fascinante mundo de la
            Programación Orientada a Objetos (POO)
            utilizando un juego que seguramente muchos de
            ustedes ya adoran: Minecraft. ¿Alguna vez se han
            preguntado cómo las herramientas, los mobs o
            incluso los bloques de Minecraft pueden ayudarnos
            a entender conceptos de programación? Bueno,
            hoy es su día de suerte. Vamos a explorar cómo los
            principios de la POO se pueden aplicar para hacer
            más fácil la comprensión de este juego lleno de
            creatividad y aventura. Prepárense para descubrir
            cómo este juego de bloques puede convertirse en
            su mejor aliado para aprender a programar de una
            manera divertida y sencilla. ¡Comencemos!

        """.trimIndent(),
        urlVideo = "https://www.youtube.com/watch?v=sqkCfZiXwjU",
        code = """
            class Pico(val material: String, var durabilidad: Int, val eficiencia: Int) {
                fun usar() {
                    if (durabilidad > 0) {
                        durabilidad -= 1
                        println("Usaste el pico de $ material. Durabilidad restante: $ durabilidad")
                    } else {
                        println("El pico está roto y no se puede usar.")
                    }
                }
            }
        """.trimIndent(),
        conclusion = """
            Este ejercicio muestra cómo las clases en Kotlin
            nos permiten organizar y reutilizar código para
            representar objetos del mundo real, como
            herramientas en Minecraft, con sus propias
            propiedades y comportamientos. Puedes
            experimentar cambiando la durabilidad y
            eficiencia de los picos o añadiendo nuevas
            propiedades y métodos para ampliar la
            funcionalidad de la clase Pico. ¡Diviértete
            explorando!
        """.trimIndent(),
        collaborators = listOf()
    )

}