package com.app.pexels.presentation.main.bottomsheet

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorBottomSheet(responseCode: Int) {

    val bottomSheetState = rememberModalBottomSheetState()
    var isBottomSheetOpen by rememberSaveable { mutableStateOf(true) }

    if (isBottomSheetOpen) {
        ModalBottomSheet(
            modifier = Modifier,
            sheetState = bottomSheetState,
            onDismissRequest = { isBottomSheetOpen = false },
            shape = RoundedCornerShape(
                topStart = 30.dp,
                topEnd = 30.dp
            ),
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            BottomSheetContainer(
                onCloseClick = { isBottomSheetOpen = false },
                responseCode = responseCode
            )
        }
    }
}
