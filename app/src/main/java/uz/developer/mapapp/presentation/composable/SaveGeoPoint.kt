package uz.developer.mapapp.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import uz.developer.mapapp.data.GeoPoints
import uz.developer.mapapp.presentation.theme.MapAppTheme

@Composable
fun SaveGeoPoint(
    latLng: LatLng,
    onPointSaved: (GeoPoints) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            "${latLng.latitude}, ${latLng.latitude}",
            color = Color.White,
            modifier = Modifier
                .padding(10.dp)
        )

        TextButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .clickable {
                        onPointSaved(
                            GeoPoints(
                                point = latLng,
                                hideSavePointUI = false
                            )
                        )
                    },
                text = "Save Point",
                color = Color.White,
                textAlign = TextAlign.End
            )
        }

    }
}

@Composable
@Preview
fun SaveGeoPointPreview() {
    MapAppTheme {
        val latLng = LatLng(0.0, 0.0)
        SaveGeoPoint(latLng) {
            GeoPoints(
                point = latLng,
                hideSavePointUI = true
            )
        }
    }
}