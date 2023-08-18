package uz.developer.mapapp.presentation.screen

import android.Manifest
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import uz.developer.mapapp.R
import uz.developer.mapapp.permissions.PermissionActions
import uz.developer.mapapp.permissions.PermissionDialog
import uz.developer.mapapp.presentation.composable.MapView

@Composable
fun MapScreenContent(
    snackBarHostState: SnackbarHostState,
    fetchLocationUpdates: () -> Unit
){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var showMap by rememberSaveable {
        mutableStateOf(false)
    }
    PermissionDialog(
        context = context,
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        permissionRational = stringResource(id = R.string.pemission_location_rationale),
        snackBarHostState = snackBarHostState
    ) {
        permissionActions ->
        when(permissionActions){
            is PermissionActions.PermissionDenied -> {
                showMap = false
            }
            is PermissionActions.PermissionGranted -> {
                showMap = true
                scope.launch {
                    snackBarHostState.showSnackbar("Location permission granted!")
                }
                fetchLocationUpdates.invoke()
            }
        }
    }
    val currentLocation = LatLng(41.367454746379224, 69.28008336455396)
    if (showMap){
        MapView(context = context, location = currentLocation)
    }
}