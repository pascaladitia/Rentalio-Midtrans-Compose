package com.pascal.rentalio.ui.screen.help

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pascal.rentalio.ui.component.Search
import com.pascal.rentalio.ui.screen.history.HistoryHeader
import com.pascal.rentalio.ui.screen.history.HistoryItem
import com.pascal.rentalio.ui.theme.RentalioTheme

@Composable
fun HelpScreen(
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
        HelpContent()
    }
}

@Composable
fun HelpContent(
    modifier: Modifier = Modifier
) {
    Column {
        Column(
            modifier = modifier.weight(1f)
        ) {
            HelpHeader()

            Column(
                modifier = modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Search(
                    modifier = Modifier.fillMaxWidth()
                ) {

                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier,
                    text = "Frequently Asked Question",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 14.sp,
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = "How do I cancel my booking?",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 14.sp,
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididun" +
                                "t ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp,
                        ),
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = "How do I cancel my booking?",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 14.sp,
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididun" +
                                "t ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp,
                        ),
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = "How do I cancel my booking?",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 14.sp,
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididun" +
                                "t ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp,
                        ),
                        color = Color.Gray
                    )
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
fun HelpHeader(
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
                text = "Help",
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

@Preview(showBackground = true)
@Composable
private fun HelpPreview() {
    RentalioTheme {
        HelpContent()
    }
}