package uz.developer.mapapp.permissions

sealed class PermissionActions {
    object PermissionGranted : PermissionActions()
    object PermissionDenied : PermissionActions()
}