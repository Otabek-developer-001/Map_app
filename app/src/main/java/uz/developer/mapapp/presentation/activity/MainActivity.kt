package uz.developer.mapapp.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import uz.developer.mapapp.presentation.GeoMakerViewModel
import uz.developer.mapapp.presentation.navigation.AppNavigation
import uz.developer.mapapp.presentation.theme.MapAppTheme
import uz.developer.mapapp.utills.locationFlow

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    private val geoMarkerViewModel: GeoMakerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackBarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            MapAppTheme {
                AppNavigation(
                    navController = navController,
                    snackBarHostState = snackBarHostState,
                    geoMarkerViewModel = geoMarkerViewModel,
                    this::fetchLocationUpdates
                )
            }
        }
    }
    private fun fetchLocationUpdates(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                fusedLocationClient.locationFlow().collect(){
                    it?.let { location ->
                        geoMarkerViewModel.setCurrentLatLng(LatLng(location.latitude,location.longitude))
                    }
                }
            }
        }
    }
}