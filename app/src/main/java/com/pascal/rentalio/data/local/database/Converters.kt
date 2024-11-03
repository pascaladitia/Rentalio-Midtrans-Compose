package com.pascal.rentalio.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pascal.rentalio.domain.model.Booking
import com.pascal.rentalio.domain.model.Metadatas
import com.pascal.rentalio.domain.model.Records

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromMetadata(metadata: Metadatas?): String? {
        return gson.toJson(metadata)
    }

    @TypeConverter
    fun toMetadata(metadataString: String?): Metadatas? {
        return gson.fromJson(metadataString, Metadatas::class.java)
    }

    @TypeConverter
    fun fromRecord(record: Records?): String? {
        return gson.toJson(record)
    }

    @TypeConverter
    fun toRecord(recordString: String?): Records? {
        return gson.fromJson(recordString, Records::class.java)
    }

    @TypeConverter
    fun fromBookingList(bookingList: List<Booking>?): String? {
        return gson.toJson(bookingList)
    }

    @TypeConverter
    fun toBookingList(bookingListString: String?): List<Booking>? {
        val bookingListType = object : TypeToken<List<Booking>>() {}.type
        return gson.fromJson(bookingListString, bookingListType)
    }
}

