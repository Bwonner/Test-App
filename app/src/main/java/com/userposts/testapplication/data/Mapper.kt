package com.userposts.testapplication.data

import com.userposts.testapplication.data.db.entity.*
import com.userposts.testapplication.data.network.dto.*
import com.userposts.testapplication.domain.model.UserPostResult

fun UserPostResultEntity.toUserPostResult(): UserPostResult =
    UserPostResult(
        name = name,
        title = title
    )

fun PostResponse.toPostEntity(): PostEntity =
    PostEntity(
        id = id,
        userId = userId,
        title = title,
        body = body
    )

fun UserResponse.toUserEntity(): UserEntity =
    UserEntity(
        id = id,
        name = name,
        username = username,
        email = email,
        address = address.toAddressEntity(),
        phone = phone,
        website = website,
        companyInfo = companyInfo.toCompanyEntity()
    )

fun AddressInfo.toAddressEntity(): AddressEntity =
    AddressEntity(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        geo = geoInfo.toGeoEntity()
    )

fun CompanyInfo.toCompanyEntity(): CompanyEntity =
    CompanyEntity(
        companyName = name,
        catchPhrase = catchPhrase,
        bs = bs
    )

fun GeoInfo.toGeoEntity(): GeoEntity =
    GeoEntity(
        lat = lat,
        lng = lng
    )