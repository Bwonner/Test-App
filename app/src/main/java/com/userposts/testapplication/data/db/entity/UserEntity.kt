package com.userposts.testapplication.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "email")
    val email: String,
    @Embedded
    val address: AddressEntity,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "website")
    val website: String,
    @Embedded
    val companyInfo: CompanyEntity
)

data class AddressEntity(
    @ColumnInfo(name = "street")
    val street: String,
    @ColumnInfo(name = "suite")
    val suite: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "zipcode")
    val zipcode: String,
    @Embedded
    val geo: GeoEntity
)

data class CompanyEntity(
    @ColumnInfo(name = "companyName")
    val companyName: String,
    @ColumnInfo(name = "catchPhrase")
    val catchPhrase: String,
    @ColumnInfo(name = "bs")
    val bs: String
)

data class GeoEntity(
    @ColumnInfo(name = "lat")
    val lat: Double,
    @ColumnInfo(name = "lng")
    val lng: Double
)
