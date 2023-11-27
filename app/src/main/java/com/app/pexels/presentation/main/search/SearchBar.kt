package com.app.pexels.presentation.main.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pexels.R
import com.app.pexels.ui.theme.CustomMulishW400

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    onSearch: (String) -> Unit,
    chipText: String,
    onChipSelected: (String) -> Unit,
) {

    var queryText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(chipText) {
        queryText = chipText
        onSearch(queryText)
    }

    LaunchedEffect(queryText) {
        onChipSelected(queryText)
    }

    TextField(
        value = queryText,
        onValueChange = { newText ->
            queryText = newText
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_input_hint),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 14.sp,
                fontFamily = CustomMulishW400,
            )
        },
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .height(50.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(queryText)
            keyboardController?.hide()
            focusManager.clearFocus()
        }),
        trailingIcon = {
            if (queryText.isNotEmpty()) {
                IconButton(
                    onClick = { queryText = "" },
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clear),
                            contentDescription = null
                        )
                    }
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            focusedContainerColor = Transparent,
            unfocusedContainerColor = Transparent,
            cursorColor = MaterialTheme.colorScheme.inversePrimary,
            focusedTextColor = MaterialTheme.colorScheme.inversePrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.inversePrimary,
            selectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.secondary,
                backgroundColor = MaterialTheme.colorScheme.secondary,
            ),
        ),
    )
}
