package com.pascal.rentalio.ui.screen.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Announcement
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Announcement
import androidx.compose.material.icons.outlined.CardGiftcard
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.EmojiEmotions
import androidx.compose.material.icons.outlined.Gif
import androidx.compose.material.icons.outlined.GifBox
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pascal.rentalio.R
import com.pascal.rentalio.ui.component.Search
import com.pascal.rentalio.ui.theme.RentalioTheme

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var isContentVisible by remember { mutableStateOf(false) }



    Surface(
        modifier = modifier.padding(paddingValues),
        color = MaterialTheme.colorScheme.background
    ) {
        AccountContent()
    }
}

@Composable
fun AccountContent(
    modifier: Modifier = Modifier
) {
    Column {
        Column(
            modifier = modifier.weight(1f)
        ) {
            AccountHeader()

            Column(
                modifier = modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(56.dp),
                        painter = painterResource(R.drawable.no_profile),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            modifier = Modifier,
                            text = "Pascal Aditia Muclis",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontSize = 14.sp,
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "pascaladitia@gmail.com",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp,
                            ),
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.onSurface)
                        .padding(12.dp)
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Rentalio Pocket",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 14.sp,
                        ),
                        color = MaterialTheme.colorScheme.background,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        modifier = Modifier,
                        text = "Rp. 1.250.000,00",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 24.sp,
                        ),
                        color = MaterialTheme.colorScheme.background,
                        textAlign = TextAlign.Center
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(12.dp)
                ) {
                    AccountMenu(
                        icons = Icons.Outlined.Person,
                        title = "Personal Information"
                    ) {

                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    AccountMenu(
                        icons = Icons.Outlined.DateRange,
                        title = "Bookings"
                    ) {

                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    AccountMenu(
                        icons = Icons.Outlined.CardGiftcard,
                        title = "Promos"
                    ) {

                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    AccountMenu(
                        icons = Icons.Outlined.Person,
                        title = "Notification"
                    ) {

                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(12.dp)
                ) {
                    AccountMenu(
                        icons = Icons.Outlined.StarOutline,
                        title = "Rate Our App"
                    ) {

                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    AccountMenu(
                        icons = Icons.Outlined.Settings,
                        title = "Settings"
                    ) {

                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    AccountMenu(
                        icons = Icons.Outlined.EmojiEmotions,
                        title = "Reviews"
                    ) {

                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    AccountMenu(
                        icons = Icons.AutoMirrored.Outlined.Announcement,
                        title = "About Us"
                    ) {

                    }
                }
            }

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
fun AccountHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(MaterialTheme.colorScheme.onSurface)
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Account",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 14.sp,
                ),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
fun AccountMenu(
    modifier: Modifier = Modifier,
    icons: ImageVector = Icons.Default.Home,
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = icons,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Icon(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterEnd),
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountPreview() {
    RentalioTheme {
        AccountContent()
    }
}