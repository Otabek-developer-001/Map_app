package uz.developer.mapapp.permissions

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import uz.developer.mapapp.utills.checkIfPermissionGranted
import uz.developer.mapapp.utills.shouldShowPermissionRationale

@Composable
fun PermissionDialog(
    context: Context,
    permission: String,
    permissionRational: String,
    snackBarHostState: SnackbarHostState,
    permissionActions: (PermissionActions) -> Unit
) {
    val permissionGranted = checkIfPermissionGranted(context,permission)

    if (permissionGranted){
        permissionActions(PermissionActions.PermissionGranted)
        return
    }
    val permissionsLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            permissionActions(PermissionActions.PermissionGranted)
        } else {
            permissionActions(PermissionActions.PermissionDenied)
        }
    }

    val showPermissionRationale = shouldShowPermissionRationale(context, permission)

    if (showPermissionRationale) {
        LaunchedEffect(showPermissionRationale) {

            val snackBarResult = snackBarHostState.showSnackbar(
                message = permissionRational,
                actionLabel = "Grant Access",
                duration = SnackbarDuration.Long

            )
            when (snackBarResult) {
                SnackbarResult.Dismissed -> {
                    permissionActions(PermissionActions.PermissionDenied)
                }
                SnackbarResult.ActionPerformed -> {
                    permissionsLauncher.launch(permission)
                }
            }
        }
    } else {
        SideEffect {
            permissionsLauncher.launch(permission)
        }

    }
}