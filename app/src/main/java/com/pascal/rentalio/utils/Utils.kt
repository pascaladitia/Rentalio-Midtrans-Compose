package com.pascal.rentalio.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.pascal.rentalio.utils.AppConstants.Companion.FORMAT_DATE
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun getCurrentFormattedDate(): String {
    val currentDate = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
    return dateFormat.format(currentDate)
}

fun reFormatDate(date: String?): Pair<String?, String?> {
    if (date.isNullOrBlank()) {
        return Pair("", "")
    }

    try {
        val inputFormat = SimpleDateFormat(FORMAT_DATE, Locale.getDefault())
        val outputFormatDateMonth = SimpleDateFormat("MMM dd", Locale.getDefault())
        val outputFormatYear = SimpleDateFormat("yyyy", Locale.getDefault())

        val dateObject = inputFormat.parse(date)

        if (dateObject != null) {
            val formattedDateMonth = outputFormatDateMonth.format(dateObject)
            val formattedYear = outputFormatYear.format(dateObject)

            return Pair(formattedDateMonth, formattedYear)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return Pair("", "")
}

fun getLocationWithCheckNetworkAndGPS(context: Context): Location? {
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return null
    }

    val lm = (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager)
    val isNetworkLocationEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    var networkLocation: Location? = null
    var gpsLocation: Location? = null
    var finalLoc: Location? = null

    try {
        gpsLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (isNetworkLocationEnabled) {
            networkLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        }

        finalLoc = if (gpsLocation != null && networkLocation != null) {
            if (gpsLocation.accuracy > networkLocation.accuracy) networkLocation
            else gpsLocation
        } else {
            gpsLocation ?: networkLocation
        }

    } catch (e: SecurityException) {
        e.printStackTrace()
    }

    return finalLoc
}

fun getAddressFromLocation(context: Context, location: Location?): String {
    val geocoder = Geocoder(context, Locale.getDefault())
    try {
        val addresses = geocoder.getFromLocation(location?.latitude ?: 0.0, location?.longitude ?: 0.0, 1)
        if (!addresses.isNullOrEmpty()) {
            val address = addresses[0]
            return address.getAddressLine(0) ?: "Unknown Address"
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return "Error getting address"
}


fun intentActionView(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    ContextCompat.startActivity(context, intent, null)
}

fun JSONObject.toRequestBody(): RequestBody {
    return this.toString().toRequestBody("application/json".toMediaTypeOrNull())
}

@SuppressLint("SimpleDateFormat")
fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
    return image
}

fun Uri.toFile(context: Context): File? {
    return try {
        val contentResolver = context.contentResolver
        val fileName = "${System.currentTimeMillis()}." + getFileExtension(contentResolver)

        val file = File(context.cacheDir, fileName)

        contentResolver.openInputStream(this)?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        file
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

@Throws(IOException::class)
private fun Uri.getFileExtension(contentResolver: ContentResolver): String {
    var extension: String? = null

    if (ContentResolver.SCHEME_CONTENT == scheme && contentResolver.getType(this)?.startsWith("image/") == true) {
        val cursor: Cursor? = contentResolver.query(this, arrayOf(MediaStore.Images.ImageColumns.MIME_TYPE), null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndex(MediaStore.Images.ImageColumns.MIME_TYPE)
                if (columnIndex != -1) {
                    val mimeType = it.getString(columnIndex)
                    extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
                }
            }
        }
    }

    if (extension.isNullOrEmpty()) {
        extension = MimeTypeMap.getFileExtensionFromUrl(toString())
    }

    if (extension.isNullOrEmpty()) {
        extension = "jpg"
    }

    return extension!!
}