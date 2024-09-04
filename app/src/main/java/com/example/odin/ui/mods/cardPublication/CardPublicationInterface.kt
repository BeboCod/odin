package com.example.odin.ui.mods.cardPublication

/**
 * Interfaz que define los eventos de clic posibles en una tarjeta de publicación.
 *
 * Esta interfaz facilita la separación entre la lógica de presentación y la lógica de negocio,
 * promoviendo la modularidad y la facilidad de prueba del código.
 */
interface CardPublicationInterface {

    /**
     * Se invoca cuando se hace clic en la tarjeta de publicación.
     *
     * Este evento puede utilizarse para acciones como expandir la tarjeta o mostrar más detalles.
     * Ejemplo de uso: Mostrar una vista detallada de la publicación.
     */
    fun onClickCard()

    /**
     * Se invoca cuando se hace clic en el botón de "me gusta".
     *
     * Este evento se utiliza para actualizar el estado de "me gusta" de la publicación.
     * Ejemplo de uso: Alternar el estado de "me gusta" y actualizar el conteo de "me gusta".
     */
    fun onClickLike()

    /**
     * Se invoca cuando se hace clic en el botón de compartir.
     *
     * Este evento se utiliza para implementar la funcionalidad de compartir la publicación en otras aplicaciones.
     * Ejemplo de uso: Abrir un menú de compartir con opciones para redes sociales y otras aplicaciones.
     */
    fun onClickShare()

    /**
     * Se invoca cuando se hace clic en el botón de información.
     *
     * Este evento se utiliza para mostrar u ocultar un menú desplegable con información adicional sobre la publicación.
     *
     * @param isBottomSheetOpen Indica si el menú desplegable está actualmente abierto.
     * Ejemplo de uso: Alternar la visibilidad de un menú de información adicional.
     */
    fun onClickInfo(isBottomSheetOpen: Boolean)

    /**
     * Se invoca cuando se hace clic en un chip (etiqueta) asociado a la publicación.
     *
     * Este evento puede utilizarse para filtrar publicaciones o realizar otras acciones basadas en la etiqueta.
     * Ejemplo de uso: Filtrar publicaciones por etiquetas o categorías.
     */
    fun onClickChip()

    /**
     * Se invoca cuando se hace clic en el botón de comentarios.
     *
     * Este evento se utiliza para navegar a la sección de comentarios de la publicación.
     * Ejemplo de uso: Mostrar la lista de comentarios o abrir una vista de comentarios.
     */
    fun onClickComment()

    /**
     * Se invoca cuando se hace clic en el perfil del usuario que publicó la tarjeta.
     *
     * Este evento se utiliza para navegar al perfil del usuario.
     * Ejemplo de uso: Abrir el perfil del usuario para ver su información y publicaciones.
     */
    fun onClickProfile()
}
