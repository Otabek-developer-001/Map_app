package uz.developer.mapapp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState
import uz.developer.mapapp.presentation.GeoMakerViewModel
import uz.developer.mapapp.presentation.composable.GeoMakerButton
import uz.developer.mapapp.presentation.composable.GeoMarkerTopBar
import uz.developer.mapapp.presentation.composable.SaveGeoPoint

@ExperimentalMaterial3Api
@Composable
fun GeoMarkerScreen(
    geoMarkerViewModel: GeoMakerViewModel
) {
    var areaPoints = mutableListOf<LatLng>()
    var drawPolygon by remember {
        mutableStateOf(false)
    }

    val currentLocation by geoMarkerViewModel.currentLatLng.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 16f)
    }

    var showSavePoint by remember {
        mutableStateOf(false)
    }

    var clickedLocation by remember {
        mutableStateOf(LatLng(0.0, 0.0))
    }

    Scaffold(
        topBar = { GeoMarkerTopBar() },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    onMapClick = {
                        if (!drawPolygon) {
                            showSavePoint = true
                            clickedLocation = it
                        }

                    }
                ) {
                    if (drawPolygon && areaPoints.isNotEmpty()) {
                        areaPoints.forEach {
                            Marker(state = MarkerState(position = it))
                        }

                        Polygon(
                            points = areaPoints,
                            fillColor = Color.Blue,
                            strokeColor = Color.Blue
                        )
                    }
                    if (showSavePoint) {
                        Marker(state = MarkerState(position = clickedLocation))
                    }

                }
                if (showSavePoint) {
                    SaveGeoPoint(latLng = clickedLocation) {
                        showSavePoint = it.hideSavePointUI
                        areaPoints.add(it.point)
                    }
                } else {
                    if (areaPoints.isEmpty()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = Color.Blue,
                            text = "Click any point on the map to mark it.",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


                GeoMakerButton(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, bottom = 16.dp)
                        .align(Alignment.BottomCenter),
                    drawPolygon = drawPolygon,
                    areaPoints = areaPoints
                ) { drawPolygonCallback ->
                    drawPolygon = drawPolygonCallback
                    if (!drawPolygonCallback) {
                        areaPoints = mutableListOf()
                    }
                }
            }
        }
    )


}