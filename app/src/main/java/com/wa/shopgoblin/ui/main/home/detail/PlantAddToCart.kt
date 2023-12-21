package com.wa.shopgoblin.ui.main.home.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R

@Composable
fun AddToCartButton(
    enabled: Boolean = true,
    onClickCart: () -> Unit = {}
) {
    val background = if (enabled) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp, top = 12.dp),
        shape = RoundedCornerShape(32.dp),
        color = background,
        shadowElevation = 8.dp,
        onClick = { onClickCart() },
        enabled = enabled
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.home_cart_icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = stringResource(id = R.string.home_detail_cart_title))
        }
    }
}

@Composable
fun TotalPriceTab(
    modifier: Modifier = Modifier,
    price: Double,
    button: @Composable () -> Unit
) {
    Row(
        modifier = modifier.wrapContentHeight(),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.padding(bottom = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.home_detail_total_title),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "$$price",
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Spacer(modifier = Modifier.size(32.dp))
        button()
    }
}