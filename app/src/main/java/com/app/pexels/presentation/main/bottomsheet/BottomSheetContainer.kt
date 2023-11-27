package com.app.pexels.presentation.main.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pexels.R
import com.app.pexels.ui.theme.CustomMulishW700
import com.app.pexels.ui.theme.White

@Composable
fun BottomSheetContainer(
    onCloseClick: () -> Unit,
    responseCode: Int,
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(responseCode),
            modifier = Modifier,
            fontSize = 20.sp,
            fontFamily = CustomMulishW700,
            textAlign = TextAlign.Center
        )
        BottomSheetImage(R.raw.conection_lost)
        Button(
            modifier = Modifier.padding(bottom = 10.dp),
            onClick = { onCloseClick() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = stringResource(R.string.ok),
                color = White,
                fontFamily = CustomMulishW700
            )
        }
    }
}
