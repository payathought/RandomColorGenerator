package com.example.randomcolorgenerator.room_local_database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tblSchemeNameList")
data class ColorGeneratorEntities(

    @ColumnInfo(name = "scheme_name") var schemeName: String?,
    @ColumnInfo(name = "color_hex") var hex_value: String?
)
{
        @PrimaryKey(autoGenerate = true) var schemeName_id: Int = 0
//    @ColumnInfo(name = "colorHexList")  var colorHexList: ArrayList<String>



}

//@Entity
//    (
//        foreignKeys = [ForeignKey
//            (
//                parentColumns = arrayOf("schemeName_id"),
//                childColumns = arrayOf("colorHex_id"),
//                onDelete = ForeignKey.CASCADE,
//                entity = ColorGeneratorEntities::class
//            )]
//    )
//data class ColorHexEntities
//    (
//        @PrimaryKey
//        val colorHex_id: Int,
//        @ColumnInfo(name = "hex_value")
//        var hexValue: String?
//) {
//
//
//
//
//
//
//}


