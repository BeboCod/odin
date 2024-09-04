package com.example.odin.ui.screens.center.screens.profile

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.odin.preference.PreferencesOdin
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel para manejar y exponer datos del perfil del usuario.
 *
 * @property context Contexto de la aplicación.
 */
@SuppressLint("StaticFieldLeak")
class ProfileViewModel(private val context: Context) : ViewModel(), InterfaceProfileViewModel {

    private val preferences = PreferencesOdin(context)

    /**
     * Estado de la UI que contiene información sobre el estado del perfil.
     *
     * @property openBottomSheet Indica si se debe mostrar un BottomSheet.
     * @property composeId ID del componente Compose activo.
     * @property profileData Datos del perfil del usuario.
     */
    data class UiState(
        val openBottomSheet: Boolean = false,
        val composeId: String = "",
        val profileData: ProfileData? = null
    )

    /**
     * Datos del perfil del usuario.
     *
     * @property name Nombre del usuario.
     * @property isVerified Estado de verificación del usuario.
     * @property postCount Cantidad de publicaciones del usuario.
     * @property followersCount Cantidad de seguidores del usuario.
     * @property commentsCount Cantidad de comentarios del usuario.
     */
    data class ProfileData(
        val name: String = "",
        val isVerified: Boolean = false,
        val postCount: Int = 0,
        val followersCount: Int = 0,
        val commentsCount: Int = 0
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    /**
     * Obtiene los datos del perfil del usuario y actualiza el estado de la UI.
     *
     * Recopila los datos del perfil, como el nombre, el estado de verificación, el número de publicaciones,
     * seguidores y comentarios. Actualiza el estado de la UI con estos datos.
     */
    override suspend fun getProfileData(): ProfileData {
        // Obtener los datos del perfil desde las preferencias
        val name = preferences.getUserName().first().toString()
        val isVerified = preferences.isUserVerified().first()
        val postCount = preferences.getPostIds().first().size
        val followersCount = preferences.getFollowerIds().first().size
        val commentsCount = preferences.getCommentIds().first().size

        // Crear una instancia de ProfileData con los datos obtenidos
        val profileData = ProfileData(
            name = name,
            isVerified = isVerified,
            postCount = postCount,
            followersCount = followersCount,
            commentsCount = commentsCount
        )

        // Actualizar el estado de la UI con los datos del perfil
        _uiState.value = _uiState.value.copy(profileData = profileData)
        return profileData
    }
}
