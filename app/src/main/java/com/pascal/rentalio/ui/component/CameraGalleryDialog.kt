package com.pascal.rentalio.ui.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.pascal.rentalio.BuildConfig
import com.pascal.rentalio.R
import com.pascal.rentalio.utils.createImageFile
import java.util.Objects

@Composable
fun CameraGalleryDialog(
    showDialogCapture: Boolean? = false,
    onSelect: (Uri) -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialogCapture == true) {
        val context = LocalContext.current
        val file = context.createImageFile()
        val uri = FileProvider.getUriForFile(Objects.requireNonNull(context),
            BuildConfig.APPLICATION_ID + ".provider", file
        )

        val galleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { onSelect(it) }
        }
        val cameraLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
                onSelect(uri)
            }

        AlertDialog(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = stringResource(R.string.select_photo_source)
                    )
                    Icon(
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = "",
                        tint = colorResource(android.R.color.darker_gray),
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .clip(CircleShape)
                            .clickable { onDismiss() }
                    )
                }
            },
            text = {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                cameraLauncher.launch(uri)
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            CameraGalleryButton(
                                icon = Icons.Default.PhotoCamera,
                                text = stringResource(R.string.camera)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                galleryLauncher.launch("image/*")
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            CameraGalleryButton(
                                icon = Icons.Default.PhotoLibrary,
                                text = stringResource(R.string.gallery)
                            )
                        }
                    }
                }
            },
            containerColor = MaterialTheme.colorScheme.background,
            onDismissRequest = {},
            confirmButton = {},
            dismissButton = {}
        )
    }
}

@Composable
private fun CameraGalleryButton(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text)
    }
}