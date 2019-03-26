package com.hyun.android.roomsample.database

import android.graphics.Bitmap
import androidx.room.Entity

@Entity
data class UserEntity(
    var userId: Int = 0
    , var name: String = ""
    , var address: String = ""
    , var image: Bitmap?
)