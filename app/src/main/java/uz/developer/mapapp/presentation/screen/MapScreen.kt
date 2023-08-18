package uz.developer.mapapp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uz.developer.mapapp.presentation.composable.GeoMarkerTopBar
import uz.developer.mapapp.presentation.navigation.Screens

@ExperimentalMaterial3Api
@Composable
fun MapScreen(
    snackBarHostState: SnackbarHostState,
    navController: NavController,
    fetchLocationUpdates: () -> Unit
) {
    Scaffold(
        topBar = { GeoMarkerTopBar() },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MapScreenContent(snackBarHostState, fetchLocationUpdates)
                SnackbarHost(
                    hostState = snackBarHostState,
                    modifier = Modifier
                        .wrapContentHeight(Alignment.Bottom)
                        .align(Alignment.BottomCenter)
                )
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .padding(16.dp),
                onClick = {
                    navController.navigate(Screens.GeoMarkerScreen.route)
                },
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Create"
                    )
                },
                text = { Text("Mark Area") }
            )
        }
    )
}