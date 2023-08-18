package uz.developer.mapapp.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.developer.mapapp.presentation.GeoMakerViewModel
import uz.developer.mapapp.presentation.screen.GeoMarkerScreen
import uz.developer.mapapp.presentation.screen.MapScreen

@ExperimentalMaterial3Api
@Composable
fun AppNavigation(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
    geoMarkerViewModel: GeoMakerViewModel,
    fetchLocationUpdates: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screens.MapsScreen.route
    ){
        composable(Screens.MapsScreen.route){
            MapScreen(
                snackBarHostState = snackBarHostState,
                navController = navController,
                fetchLocationUpdates
            )
        }
        composable(Screens.GeoMarkerScreen.route){
            GeoMarkerScreen(geoMarkerViewModel)
        }
    }
}