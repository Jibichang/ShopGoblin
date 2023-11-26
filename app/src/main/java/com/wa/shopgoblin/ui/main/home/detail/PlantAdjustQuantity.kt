package com.wa.shopgoblin.ui.main.home.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R

@Composable
fun AdjustQuantityButton(
    quantity: MutableIntState = remember { mutableIntStateOf(0) }
) {
    val enableMinusButton by remember {
        derivedStateOf { quantity.intValue != 0 }
    }

    Surface(
        modifier = Modifier.wrapContentWidth(),
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { quantity.intValue = quantity.intValue - 1 },
                enabled = enableMinusButton
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.home_quantity_minus_icon),
                    contentDescription = "reduce quantity"
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = quantity.intValue.toString(),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.size(20.dp))
            IconButton(
                onClick = { quantity.intValue = quantity.intValue + 1 }) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.home_quantity_plus_icon),
                    contentDescription = "add quantity"
                )
            }
        }
    }
}

@Composable
fun AdjustQuantityTab(
    button: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.home_detail_quantity_title),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(20.dp))
        button()
    }
}