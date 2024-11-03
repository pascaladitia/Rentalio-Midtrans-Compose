package com.pascal.rentalio.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.pascal.rentalio.R
import com.pascal.rentalio.ui.theme.RentalioTheme

@Composable
fun ShowErrorDialog(
    title: String,
    message: String,
    isDialogVisible: Boolean? = false,
    onClose: () -> Unit
) {
    if (isDialogVisible == true) {
        AlertDialog(
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = false
            ),
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "",
                        tint = Color.Red,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        text = title
                    )
                }
            },
            text = {
                Text(text = message, style = MaterialTheme.typography.bodyMedium)
            },
            onDismissRequest = { onClose() },
            confirmButton = {},
            shape = RoundedCornerShape(16.dp),
            containerColor = MaterialTheme.colorScheme.background,
            dismissButton = {
                Button(onClick = { onClose() }) {
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        text = stringResource(R.string.close)
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun DialogErrorPreview() {
    RentalioTheme {
        ShowErrorDialog(
            title = "Error Dialog",
            message = "error message",
            isDialogVisible = true
        ) {
            
        }
    }
}