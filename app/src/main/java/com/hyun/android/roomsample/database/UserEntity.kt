package com.hyun.android.roomsample.database

import android.graphics.Bitmap
import androidx.room.*

@Entity(tableName = "userENTITY")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var userId: Int = 0
    , @ColumnInfo(name = "custom_NAME") var name: String = ""
    , @ColumnInfo(name = "CUSTOM_address") var address: String? = ""
    , @Ignore var image: Bitmap? = null
)