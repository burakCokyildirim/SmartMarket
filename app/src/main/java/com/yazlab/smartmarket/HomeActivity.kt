package com.yazlab.smartmarket

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.github.kittinunf.fuel.httpGet
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.nav_header_home.*
import org.json.JSONArray
import org.json.JSONObject

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, LocationListener {

    private lateinit var mMap: GoogleMap
    private lateinit var provider:String
    private var locationManager: LocationManager? = null
    private val PERMISSIONS_REQUEST_LOCATION = 100
    var mLocation:Location?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        permissionLocation()

        currentLocation.setOnClickListener {
            getCampains()

        }
    }

    private fun getCampains() {
        if (storeName.text.isEmpty() && category.text.isEmpty()){
            val url = "https://us-central1-yazlabadds.cloudfunctions.net/api/stores"
            url.httpGet().response { _, response, _ ->
                val data = String(response.data)
                val jsonArray = JSONArray(data)
                for (i in 0 until jsonArray.length()) {
                    val campain = jsonArray.getJSONObject(i)
                    val name = campain.getJSONObject("data").getString("category")
                    val category = campain.getJSONObject("data").getString("name")
                    println(name)
                    println(category)
                    val longitude = campain.getJSONObject("data")
                        .getJSONObject("location").getDouble("_longitude")
                    val latitude = campain.getJSONObject("data")
                        .getJSONObject("location").getDouble("_latitude")


                    val cLatitude = mLocation?.latitude
                    val cLongitude = mLocation?.longitude

                    val distance = distance(latitude, longitude, cLatitude!!, cLongitude!!)

                    if ( distance <= threshold.text.toString().toFloat()) {
                        val campainModel = Campain(category, name, distance)
                        UserModel.campains.add(campainModel)
                        startActivity(Intent(this, CampainListActivity::class.java))
                    }
                }
            }
        }
        else if (storeName.text.isEmpty()) {
            val url = "https://us-central1-yazlabadds.cloudfunctions.net/api/stores?category=" + category.text.toString()
            url.httpGet().response { _, response, _ ->
                val data = String(response.data)
                val jsonArray = JSONArray(data)
                for (i in 0 until jsonArray.length()) {
                    val campain = jsonArray.getJSONObject(i)
                    val name = campain.getJSONObject("data").getString("category")
                    val category = campain.getJSONObject("data").getString("name")
                    println(name)
                    println(category)
                    val longitude = campain.getJSONObject("data")
                        .getJSONObject("location").getDouble("_longitude")
                    val latitude = campain.getJSONObject("data")
                        .getJSONObject("location").getDouble("_latitude")


                    val cLatitude = mLocation?.latitude
                    val cLongitude = mLocation?.longitude

                    val distance = distance(latitude, longitude, cLatitude!!, cLongitude!!)

                    if ( distance <= threshold.text.toString().toFloat()) {
                        val campainModel = Campain(category, name, distance)
                        UserModel.campains.add(campainModel)
                        startActivity(Intent(this, CampainListActivity::class.java))
                    }
                }
            }
        } else if (category.text.isEmpty()) {
            val url = "https://us-central1-yazlabadds.cloudfunctions.net/api/stores?name=" + storeName.text.toString()
            println(url)
            url.httpGet().response { _, response, _ ->
                val data = String(response.data)
                val jsonArray = JSONArray(data)
                for (i in 0 until jsonArray.length()) {
                    val campain = jsonArray.getJSONObject(i)
                    val name = campain.getJSONObject("data").getString("category")
                    val category = campain.getJSONObject("data").getString("name")
                    println(name)
                    println(category)
                    val longitude = campain.getJSONObject("data")
                        .getJSONObject("location").getDouble("_longitude")
                    val latitude = campain.getJSONObject("data")
                        .getJSONObject("location").getDouble("_latitude")


                    val cLatitude = mLocation?.latitude
                    val cLongitude = mLocation?.longitude

                    val distance = distance(latitude, longitude, cLatitude!!, cLongitude!!)

                    if ( distance <= threshold.text.toString().toFloat()) {
                        val campainModel = Campain(category, name, distance)
                        UserModel.campains.add(campainModel)
                        startActivity(Intent(this, CampainListActivity::class.java))
                    }
                }
            }
        } else {
            val url = "https://us-central1-yazlabadds.cloudfunctions.net/api/stores?name=" + storeName.text
                .toString() + "&category=" + category.text.toString()
            url.httpGet().response { _, response, _ ->
                val data = String(response.data)
                val jsonArray = JSONArray(data)
                for (i in 0 until jsonArray.length()) {
                    val campain = jsonArray.getJSONObject(i)
                    val name = campain.getJSONObject("data").getString("category")
                    val category = campain.getJSONObject("data").getString("name")
                    println(name)
                    println(category)
                    val longitude = campain.getJSONObject("data")
                        .getJSONObject("location").getDouble("_longitude")
                    val latitude = campain.getJSONObject("data")
                        .getJSONObject("location").getDouble("_latitude")


                    val cLatitude = mLocation?.latitude
                    val cLongitude = mLocation?.longitude

                    val distance = distance(latitude, longitude, cLatitude!!, cLongitude!!)

                    if ( distance <= threshold.text.toString().toFloat()) {
                        val campainModel = Campain(category, name, distance)
                        UserModel.campains.add(campainModel)
                        startActivity(Intent(this, CampainListActivity::class.java))
                    }
                }
            }
        }
    }

    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float{
        val loc1 = Location("")
        loc1.latitude = lat1
        loc1.longitude = lon1

        val loc2 = Location("")
        loc2.latitude = lat2
        loc2.longitude = lon2

        val distanceInMeters = loc1.distanceTo(loc2)
        return distanceInMeters
    }

    private fun permissionLocation(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {



            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_LOCATION
                )
            }
            return false
        } else {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as (LocationManager)
            provider = locationManager!!.getBestProvider(Criteria(), false)

            return true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {

                        locationManager = getSystemService(Context.LOCATION_SERVICE) as (LocationManager)
                        provider = locationManager!!.getBestProvider(Criteria(), false)

                        //Request location updates:
                        locationManager!!.requestLocationUpdates(provider, 400L, 1.0f, this)
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return
            }
        }
    }



    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        textView.text = UserModel.email
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {

            }
            R.id.nav_tools -> {
                val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                drawerLayout.closeDrawer(GravityCompat.START)
                startActivity(Intent(this, SettingsActivity::class.java))
                return true
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!
    }

    override fun onLocationChanged(location: Location?) {
        mLocation = location
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

data class Campain(val category: String, val name: String, val distance: Float) {

}
