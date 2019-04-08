package com.hyun.android.roomsample.dummy

import android.graphics.Bitmap
import androidx.room.*
import com.hyun.android.roomsample.database.UserEntity

/**
 * 문법만을 위한 사용되지 않는 Entity집합
 */
@Entity(
    tableName = "DummyTable"
    , inheritSuperIndices = true
)
data class UserEntityDummy(
    @PrimaryKey var dummyId: Int = 0
    , @ForeignKey(
        entity = UserEntity::class
        , parentColumns = ["userId"]
        , childColumns = ["dummyForeignKey"]
        , onDelete = ForeignKey.CASCADE
    ) var dummyForeignKey: Int = 0
    , @ColumnInfo(name = "custom_NAME", index = true) var name: String = ""
    , @ColumnInfo(name = "CUSTOM_address", index = true) var address: String? = ""
    , @Ignore var image: Bitmap? = null
)

//부모객체 입니다.
@Entity(
    indices = arrayOf(
        Index(
            value = ["first_name", "last_name"],
            unique = true
        )
    )
)
data class User(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
    @Ignore var picture: Bitmap?
)
///////////

//자식객체 입니다.
@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class Book(
    @PrimaryKey var bookId: Int,
    var title: String?,
    @ColumnInfo(
        name = "user_id",
        typeAffinity = ColumnInfo.TEXT,
        index = true,
        collate = ColumnInfo.UNSPECIFIED
    ) var userId: Int
)
////////////