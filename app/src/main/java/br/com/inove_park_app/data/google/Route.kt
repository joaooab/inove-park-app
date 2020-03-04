package br.com.inove_park_app.data.google

import br.com.inove_park_app.data.google.Bounds
import br.com.inove_park_app.data.google.Leg
import br.com.inove_park_app.data.google.OverviewPolyline

data class Route(
    val bounds: Bounds,
    val copyrights: String,
    val legs: List<Leg>,
    val overview_polyline: OverviewPolyline,
    val summary: String,
    val warnings: List<Any>,
    val waypoint_order: List<Any>
)