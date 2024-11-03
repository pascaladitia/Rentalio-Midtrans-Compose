package com.pascal.rentalio.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.pascal.rentalio.data.local.database.Converters
import com.pascal.rentalio.data.local.database.DatabaseConstants
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseHistory(
	@field:SerializedName("record")
	@Embedded
	val records: Records,

	@field:SerializedName("metadata")
	@Embedded
	val metadata: Metadatas
) : Parcelable

@Parcelize
@Entity(tableName = DatabaseConstants.TABLE_HISTORY)
data class Booking(
	@PrimaryKey(autoGenerate = true)
	val id: Int? = null,

	@field:SerializedName("code_book")
	val codeBook: String? = null,

	@field:SerializedName("vehicle_name")
	val vehicleName: String? = null,

	@field:SerializedName("vehicle_image")
	val vehicleImage: String? = null,

	@field:SerializedName("date_start")
	val dateStart: String? = null,

	@field:SerializedName("date_end")
	val dateEnd: String? = null
) : Parcelable

@Parcelize
data class Records(
	@field:SerializedName("bookings")
    var bookings: List<Booking>
) : Parcelable

@Parcelize
data class Metadatas(
	@field:SerializedName("private")
	val private: Boolean? = null,
) : Parcelable
