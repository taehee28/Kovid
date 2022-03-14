package com.project.kovid.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * 코로나19병원정보 API DataClass
 * */
@Xml(name = "response")
data class HospData(
    @Element(name = "header")
    val header: HospHeader,
    @Element(name = "body")
    val body: HospBody
)

@Xml(name = "header")
data class HospHeader(
    @PropertyElement(name = "resultCode")
    val resultCode: Int,
    @PropertyElement(name = "resultMsg")
    val resultMsg: String,
)

@Xml(name = "body")
data class HospBody(
    @Element(name = "items")
    val items: HospItems,
    @PropertyElement(name = "numOfRows")
    val numOfRows: Int,
    @PropertyElement(name = "pageNo")
    val pageNo: Int,
    @PropertyElement(name = "totalCount")
    val totalCount: Int,
)

@Xml(name = "items")
data class HospItems(
    @Element(name = "item")
    val item: List<HospItem>
)

@Xml(name = "item")
data class HospItem(
    @PropertyElement(name = "addr") //대전광역시 중구 당디로6번길 76 302,304,305호 (문화동, 두루타운)
    var addr: String,

    @PropertyElement(name = "recuClCd") //요양종별코드 //11:종합병원 21:병원 31:의원
    var recuClCd: String,

    @PropertyElement(name = "pcrPsblYn") //PCR 가능여부
    var pcrPsblYn: String,

    @PropertyElement(name = "ratPsblYn") //RAT 가능여부
    var ratPsblYn: String,

    @PropertyElement(name = "sgguCdNm")  //시군구명
    var sgguCdNm: String,

    @PropertyElement(name = "sidoCdNm")  //시도명
    var sidoCdNm: String,

    @PropertyElement(name = "telno")   //전화번호
    var telno: String,

    @PropertyElement(name = "yadmNm")  //기관명
    var yadmNm: String,

    @PropertyElement(name = "YPosWgs84")  //위도
    var YPosWgs84: Double,

    @PropertyElement(name = "XPosWgs84")  //경도
    var XPosWgs84: Double,
)

@Entity
data class HospDBItem(
    @PrimaryKey(autoGenerate = true)
    var id : Int?,

    @ColumnInfo(name ="addr")
    var addr: String,                   //대전광역시 중구 당디로6번길 76 302,304,305호 (문화동, 두루타운)

    @ColumnInfo(name ="recuClCd")
    var recuClCd: String,               //요양종별코드 //11:종합병원 21:병원 31:의원

    @ColumnInfo(name ="pcrPsblYn")
    var pcrPsblYn: String,              //PCR 가능여부

    @ColumnInfo(name ="ratPsblYn")
    var ratPsblYn: String,              //RAT 가능여부

    @ColumnInfo(name ="sgguCdNm")
    var sgguCdNm: String,               //시군구명

    @ColumnInfo(name ="sidoCdNm")
    var sidoCdNm: String,               //시도명

    @ColumnInfo(name ="telno")
    var telno: String,                  //전화번호

    @ColumnInfo(name ="yadmNm")
    var yadmNm: String,                 //기관명

    @ColumnInfo(name ="YPosWgs84")
    var YPosWgs84: Double,              //위도

    @ColumnInfo(name ="XPosWgs84")
    var XPosWgs84: Double,              //경도
): ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(YPosWgs84, XPosWgs84)
    }

    override fun getTitle(): String {
        return yadmNm
    }

    override fun getSnippet(): String {
        return "$addr\n$telno"
    }
}





