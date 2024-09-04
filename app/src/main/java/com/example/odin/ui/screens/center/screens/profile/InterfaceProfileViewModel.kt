package com.example.odin.ui.screens.center.screens.profile

/**
 * Interfaz que define las operaciones disponibles para obtener los datos del perfil del usuario.
 */
interface InterfaceProfileViewModel {

   /**
    * Obtiene los datos del perfil del usuario.
    *
    * @return Datos del perfil del usuario.
    */
   suspend fun getProfileData(): ProfileViewModel.ProfileData
}
