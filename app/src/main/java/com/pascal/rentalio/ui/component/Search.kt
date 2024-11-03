package com.pascal.rentalio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.rentalio.ui.theme.RentalioTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.Search

@Composable
fun Search(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {

    var searchText by remember { mutableStateOf("") }

    BasicTextField(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(12.dp)),
        value = searchText,
        onValueChange = {
            searchText = it
        },
        textStyle = MaterialTheme.typography.bodySmall,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(searchText)
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            ) {
                Icon(
                    imageVector = FeatherIcons.Search,
                    contentDescription = "Arrow Down",
                    tint = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box {
                    if (searchText.isEmpty()) {
                        Text(
                            text = "Search...",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    RentalioTheme {
        Search() {

        }
    }
}