package com.app.pexels.presentation.main.stubs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pexels.R
import com.app.pexels.ui.theme.CustomMulishW700

@Composable
fun StubNoInternetConnection(
    modifier: Modifier = Modifier,
    onTryAgainClick: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .padding(bottom = 24.dp),
                painter = painterResource(id = R.drawable.ic_no_internet_connection),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inversePrimary)
            )
            Text(
                modifier = Modifier.clickable(
                    onClick = {
                        onTryAgainClick()
                    }
                ),
                text = stringResource(id = R.string.try_again),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontFamily = CustomMulishW700,
                )
            )
        }
    }
}
