package com.project.kovid.function.map

import android.app.Application
import android.location.Location
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.project.kovid.function.repository.MapRepository
import com.project.kovid.model.HospItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapsViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = MapsViewModel::class.java.simpleName
    val context: Application = application

    private val mapRepo: MapRepository = MapRepository(application)

    var symptomTestHospData = MutableLiveData<List<HospItem>>()

    /**
     *병원정보 get
     * */
    fun getHospData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = mapRepo.getSymptomTest()

                if (result.isSuccessful && result.body() != null) {
                    val resultData = result.body()!!.body.items.item
                    symptomTestHospData.postValue(resultData)
                } else {
                    Log.d(TAG, "getHospital() result not Successful or result.body null")
                }

            } catch (e: Exception) {
                Log.d(TAG, "getHospital() fail...")
            }
        }
    }

    /**
     *현위치 get
     * */
    val myLocation = MutableLiveData<Location>()

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            myLocation.value = locationResult.lastLocation
        }
    }

    fun startLocation() {
        mapRepo.startLocation(mLocationCallback)
    }

    fun stopLocation() {
        mapRepo.stopLocation(mLocationCallback)
    }

    fun permissionNotAllowCheck(): Boolean {
        return mapRepo.checkFineLocationPermission(getApplication()) && mapRepo.checkCoarseLocationPermission(getApplication())
    }
}