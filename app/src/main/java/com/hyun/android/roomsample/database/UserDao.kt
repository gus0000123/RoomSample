package com.hyun.android.roomsample.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*
import kotlin.collections.ArrayList

@Dao
interface UserDao {
    //insert 구문들
    @Insert
    fun insert(userEntity: UserEntity)

    @Insert
    fun insert(userEntity01: UserEntity, userEntity02: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg userEntity: UserEntity)

    @Insert
    fun insert(userEntity: List<UserEntity>)

    //update 구문들
    @Update
    fun update(userEntity: UserEntity)

    @Update
    fun update(userEntity01: UserEntity, userEntity02: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg userEntity: UserEntity)

    @Update
    fun update(userEntity: List<UserEntity>)

    //delete 구문들
    @Delete
    fun delete(userEntity: UserEntity)

    @Delete
    fun delete(userEntity01: UserEntity, userEntity02: UserEntity)

    @Delete
    fun delete(vararg userEntity: UserEntity)

    @Delete
    fun delete(userEntity: List<UserEntity>)


    //Simple Query
    @Query("SELECT * FROM USERENTITY")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM UserEntity where custom_NAME = :name")
    fun getWithName(name: String): List<UserEntity>

    @Query("SELECT * FROM UserEntity where custom_NAME in (:names)")
    fun getWithNames(names: ArrayList<String>): List<UserEntity>

    //Observable Query
    @Query("SELECT * FROM UserEntity")
    fun getAllObservable(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity where custom_NAME = :name")
    fun getWithNameObservable(name: String): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity where custom_NAME in (:names)")
    fun getWithNamesObservable(names: ArrayList<String>): LiveData<List<UserEntity>>
}