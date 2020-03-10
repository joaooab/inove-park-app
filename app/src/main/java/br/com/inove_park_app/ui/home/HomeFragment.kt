package br.com.inove_park_app.ui.home

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.inove_park_app.MainActivity
import br.com.inove_park_app.R
import br.com.inove_park_app.data.Park
import br.com.inove_park_app.data.google.Direction
import br.com.inove_park_app.extension.supportFragmentManager
import br.com.inove_park_app.util.InfoWindowFactory
import com.appolica.interactiveinfowindow.InfoWindow
import com.appolica.interactiveinfowindow.InfoWindowManager
import com.appolica.interactiveinfowindow.fragment.MapInfoWindowFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    companion object {
        const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
        private const val KEY_CAMERA_POSITION = "camera_position"
        private const val KEY_LOCATION = "location"
    }

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var mActivity: MainActivity
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private var mLastKnownLocation: Location? = null
    private val mDefaultLocation = LatLng(-25.443150, -49.238243)
    private val DEFAULT_ZOOM = 12
    private var mLocationPermissionGranted: Boolean = false
    private val infoWindowFactory = InfoWindowFactory()
    private var mapInfoWindowFragment: MapInfoWindowFragment? = null
    private lateinit var infoWindow: InfoWindow
    private var infoWindowManager: InfoWindowManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapInfoWindowFragment =
            childFragmentManager.findFragmentById(R.id.map) as MapInfoWindowFragment?
        infoWindowManager = mapInfoWindowFragment!!.infoWindowManager()
        infoWindowManager!!.setHideOnFling(true)
        setUpMap()
        setUpButtonPark()
        viewModel.route.observe(viewLifecycleOwner, Observer { direcetion ->
//            val route = direcetion.routes[0]
//            val pointsList = PolyUtil.decode(route.overview_polyline.points)
//            mMap.clear()
            markerDestination(direcetion)
//            mMap.addPolyline(PolylineOptions().addAll(pointsList))
//            mMap.animateCamera(updateCamera())
        })
    }

    private fun markerDestination(direcetion: Direction) {
        val destination = direcetion.routes[0].legs[0].end_location
        val markerDestination = MarkerOptions().position(
            LatLng(
                destination.lat,
                destination.lng
            )
        )
        mMap.addMarker(markerDestination)
    }

    private fun updateCamera(): CameraUpdate? {
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        val padding = (width * 0.15).toInt()
        return CameraUpdateFactory.newLatLngBounds(
            LatLngBounds.Builder().build(),
            width,
            height,
            padding
        )
    }


    private fun setUpButtonPark() {
        buttonPark.setOnClickListener {
            supportFragmentManager {
                BottomSheetParkFragment
                    .newInstance()
                    .show(this, "")
            }
        }
    }

    private fun setUpMap() {
        val supportMap = childFragmentManager.findFragmentById(R.id.map) as MapInfoWindowFragment
        supportMap.getMapAsync { map ->
            mMap = map
            getLocationPermission()
            updateLocationUI()
            getDeviceLocation()
            viewModel.getMarkerList().forEach {
                val marker = map.addMarker(it)
                marker.tag = Park("Estacionamento")
            }
            mMap.setOnMarkerClickListener {
                onMarkerClick(it)
                viewModel.getDirection(it, mLastKnownLocation)
                return@setOnMarkerClickListener true
            }
        }
    }

    fun onMarkerClick(marker: Marker): Boolean {
        val offsetX = resources.getDimension(R.dimen.marker_offset_x).toInt()
        val offsetY = resources.getDimension(R.dimen.marker_offset_y).toInt()

        val markerSpec = InfoWindow.MarkerSpecification(offsetX, offsetY)

        val windowFragment = infoWindowFactory.obterInfoWindowFragment(marker)
        if (windowFragment != null) {
            infoWindow = InfoWindow(marker, markerSpec, windowFragment)
            infoWindowManager?.toggle(infoWindow, true)
        }
        return true
    }

    private fun getDeviceLocation() {
        try {
            if (mLocationPermissionGranted) {
                val locationResult = mFusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(mActivity) { task ->
                    if (task.isSuccessful) {
                        mLastKnownLocation = task.result
                        mMap.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                viewModel.getFirstLatLong(), DEFAULT_ZOOM.toFloat()
                            )
                        )
                    } else {
                        Log.d("Device location", "Current location is null. Using defaults.")
                        Log.e("Device location", "Exception: %s", task.exception)
                        mMap.moveCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM.toFloat())
                        )
                        mMap.uiSettings.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message)
        }

    }

    private fun getLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                context!!,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mLocationPermissionGranted = true
        } else {
            requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        mLocationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true
                }
            }
        }

        updateLocationUI()
    }

    private fun updateLocationUI() {
        try {
            if (mLocationPermissionGranted) {
                mMap.isMyLocationEnabled = true
                mMap.uiSettings.isMyLocationButtonEnabled = true
            } else {
                mMap.isMyLocationEnabled = false
                mMap.uiSettings.isMyLocationButtonEnabled = false
                mLastKnownLocation = null
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message)
        }
    }

}