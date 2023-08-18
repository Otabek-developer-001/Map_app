package uz.developer.mapapp.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uz.developer.mapapp.presentation.theme.MapAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeoMarkerTopBar() {
    TopAppBar(
        title = { Text("Map app") },
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.inversePrimary)
    )
}

@Preview
@Composable
fun TopBarPreview() {
    MapAppTheme() {
        GeoMarkerTopBar()
    }
}