package uz.developer.mapapp.data

import com.google.android.gms.maps.model.LatLng

data class GeoPoints(
    val point: LatLng,
    val hideSavePointUI: Boolean = true
)