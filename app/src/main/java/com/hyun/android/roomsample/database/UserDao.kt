package com.hyun.android.roomsample.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM userentity")
    fun getAll(): List<UserEntity>

    @Insert
    fun insert(userEntity: UserEntity)
}