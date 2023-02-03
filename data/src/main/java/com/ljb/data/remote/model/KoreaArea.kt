package com.ljb.data.remote.model

import com.google.gson.annotations.SerializedName

data class KoreaArea(
    @SerializedName("resultCode") var resultCode : Int,
    @SerializedName("resultMessage") var resultMessage : String,

    //@SerializedName("korea") var korea : AreaData,
    @SerializedName("seoul") var seoul : AreaResponse,
    @SerializedName("busan") var busan : AreaResponse,
    @SerializedName("daegu") var daegu : AreaResponse,
    @SerializedName("incheon") var incheon : AreaResponse,
    @SerializedName("gwangju") var gwangju : AreaResponse,
    @SerializedName("daejeon") var daejeon : AreaResponse,
    @SerializedName("ulsan") var ulsan : AreaResponse,
    @SerializedName("sejong") var sejong : AreaResponse,
    @SerializedName("gyeonggi") var gyeonggi : AreaResponse,
    @SerializedName("gangwon") var gangwon : AreaResponse,
    @SerializedName("chungbuk") var chungbuk : AreaResponse,
    @SerializedName("chungnam") var chungnam : AreaResponse,
    @SerializedName("jeonbuk") var jeonbuk : AreaResponse,
    @SerializedName("jeonnam") var jeonnam : AreaResponse,
    @SerializedName("gyeongbuk") var gyeongbuk : AreaResponse,
    @SerializedName("gyeongnam") var gyeongnam : AreaResponse,
    @SerializedName("jeju") var jeju : AreaResponse,
    @SerializedName("quarantine") var quarantine : AreaResponse, //검역
)

data class AreaResponse(
    @SerializedName("countryName") var countryName : String, //시도명(지역명)
    @SerializedName("newCase") var newCase : String,         //신규확진환자수
    @SerializedName("totalCase") var totalCase : String,     //확진환자수
    @SerializedName("recovered") var recovered : String,     //완치자수
    @SerializedName("death") var death : String,             //사망자
    @SerializedName("percentage") var percentage : String,   //발생률
    @SerializedName("newCcase") var newCcase : String,       //전일대비증감-해외유입
    @SerializedName("newFcase") var newFcase : String,       //전일대비증감-지역발생
)