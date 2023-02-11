package com.project.kovid.widget.extension.customview

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.ljb.data.model.SelectiveCluster
import com.project.kovid.R
import com.project.kovid.widget.util.CanvasUtil


class HospClusterMarker(private val context: Context, map: GoogleMap, clusterManager: ClusterManager<SelectiveCluster>)
    : DefaultClusterRenderer<SelectiveCluster>(context, map, clusterManager) {
    companion object{
        const val HOSP_COMPREHENSIVE = "11"
        const val HOSP_GENERAL = "21"
        const val HOSP_DOCTOR_OFFICE = "31"
    }

    override fun onBeforeClusterItemRendered(item: SelectiveCluster, markerOptions: MarkerOptions) {
        /*val id: Int = when(item.recuClCd){
            HOSP_COMPREHENSIVE -> R.drawable.ic_hosp_comprehensive
            HOSP_GENERAL -> R.drawable.ic_hosp_general
            HOSP_DOCTOR_OFFICE -> R.drawable.ic_hosp_doctor_office
            else -> R.drawable.ic_hosp_comprehensive
        }*/

        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_hosp_doctor_office)
        val markerIcon: BitmapDescriptor = CanvasUtil.drawableToBitmapDescriptor(drawable!!)

        markerOptions.icon(markerIcon)
        markerOptions.snippet(item.snippet)
        markerOptions.title(item.title)
        markerOptions.position(LatLng(item.lat,item.lng))
        //super.onBeforeClusterItemRendered(item, markerOptions)
    }

    override fun onClusterItemRendered(clusterItem: SelectiveCluster, marker: Marker) {
        //super.onClusterItemRendered(clusterItem, marker)
        marker.tag = clusterItem
    }
}