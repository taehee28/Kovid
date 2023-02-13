package com.project.kovid.widget.extension

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

/**
 * Provide [AppModule]
 * */
class MyLocationManager @Inject constructor(@ApplicationContext val context: Context) {
    val TAG = LocationManager::class.java.name

    private var mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context) // 현재 위치를 가져오기 위한 변수
    private lateinit var mLocationRequest: LocationRequest // 위치 정보 요청의 매개변수를 저장하는

    @SuppressLint("MissingPermission")
    fun startLocationUpdates(mLocationCallback: LocationCallback) {
        mLocationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper()!!)
    }

    fun stopLocationUpdates(mLocationCallback: LocationCallback) {
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback)
    }

    //주소로 위도,경도 구하는 GeoCoding
    fun getGeoCoding(address: String): Location {
        return try {
            with(Geocoder(context, Locale.KOREA).getFromLocationName(address, 1)){
                if (this.size == 0){
                    Log.d("GeoCoding", "해당 주소로 찾은 위도 경도가 없습니다. $address")

                    Location("").apply {
                        latitude = 37.554891
                        longitude = 126.970814
                    } //임시 서울역
                }else{
                    Location("").apply {
                        latitude = this.latitude
                        longitude = this.longitude
                    }
                }
            }
        }catch (e:Exception) {
            e.printStackTrace()
            getGeoCoding(address) //재시도
        }
    }

    // 위도 경도로 주소 구하는 Reverse-GeoCoding
    fun getReverseGeocoding(position: LatLng): String {
        // Geocoder 로 자기 나라에 맞게 설정
        val geoCoder = Geocoder(context, Locale.KOREA)
        var addr = "주소 오류"

        //GRPC 오류? try catch 문으로 오류 대처
        try {
            addr = geoCoder.getFromLocation(position.latitude, position.longitude, 1).first()
                .getAddressLine(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return addr
    }


    // 위도 경도로 주소 구하는 Reverse-GeoCoding
    fun reverseGeoCoding(location: Location) : Pair<String, String>{
        return try {
            // Geocoder 로 자기 나라에 맞게 설정
            with(Geocoder(context, Locale.KOREA).getFromLocation(location.latitude, location.longitude, 1).first()){
                Pair(adminArea, locality) //ex. 서울특별시, 마포구
            }
        } catch (e: Exception) { //GRPC 오류시 재시도
            e.printStackTrace()
            reverseGeoCoding(location)
        }
    }
}
