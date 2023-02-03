package com.project.kovid.function.repository

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.project.kovid.BuildConfig
import com.project.kovid.RetrofitObject
import com.project.kovid.database.HospDao
import com.project.kovid.database.HospDatabase
import com.project.kovid.function.map.HospitalAPI
import com.project.kovid.widget.extension.LocationManager
import com.project.kovid.model.HospDBItem
import com.project.kovid.model.HospData
import retrofit2.Response

class MapRepository(application: Application) {
    private val hospitalRetrofit : retrofit2.Retrofit = RetrofitObject.getRetrofitHospital()
    private val hospitalApi = hospitalRetrofit.create(HospitalAPI::class.java)

    companion object{
        const val HOSP_COMPREHENSIVE = "11"
        const val HOSP_GENERAL = "21"
        const val HOSP_DOCTOR_OFFICE = "31"
    }
    suspend fun getSymptomTest() : Response<HospData> = hospitalApi.getHospital(BuildConfig.DATA_GO_KR_API_KEY,1, 1000, "97")

    val locationLoader = LocationManager(application)

    fun startLocation(mLocationCallback: LocationCallback){
        locationLoader.startLocationUpdates(mLocationCallback)
    }

    fun stopLocation(mLocationCallback: LocationCallback){
        locationLoader.stopLocationUpdates(mLocationCallback)
    }

    fun checkFineLocationPermission(context: Context): Boolean =
        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    fun checkCoarseLocationPermission(context: Context): Boolean =
        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED



    private val hospDatabase: HospDatabase = HospDatabase.getInstance(application)!!
    private val hospDao: HospDao = hospDatabase.hospDao()

    fun getAll(): List<HospDBItem> {
        return hospDao.getAll()
    }

    fun insert(hospDBItem: HospDBItem){
        hospDao.insert(hospDBItem)
    }
    fun delete(hospDBItem: HospDBItem){
        hospDao.delete(hospDBItem)
    }

    fun update(hospDBItem: HospDBItem){
        hospDao.update(hospDBItem)
    }
}