package uz.developer.mapapp.presentation.navigation

sealed class Screens(var route:String){
    object MapsScreen : Screens("mapScreen")
    object GeoMarkerScreen : Screens("geoMarkerScreen")
}