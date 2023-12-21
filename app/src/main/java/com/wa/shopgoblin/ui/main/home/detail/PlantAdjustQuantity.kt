package com.wa.shopgoblin.ui.main.home.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R

@Composable
fun AdjustQuantityButton(
    smalled: Boolean = false,
    quantity: MutableIntState = remember { mutableIntStateOf(0) }
) {
    val enableMinusButton by remember {
        derivedStateOf { quantity.intValue != 0 }
    }

    Surface(
        modifier = Modifier.wrapContentWidth().height(smalled.height()),
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier.padding(paddingValues = smalled.paddingValues()),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { quantity.intValue = quantity.intValue - 1 },
                enabled = enableMinusButton
            ) {
                Icon(
                    modifier = Modifier.size(smalled.iconSize()),
                    painter = painterResource(id = R.drawable.home_quantity_minus_icon),
                    contentDescription = "reduce quantity"
                )
            }
            Text(
                text = quantity.intValue.toString(),
                style = smalled.fontStyle(),
                modifier = Modifier.padding(
                    start = smalled.spaceBetween(),
                    end = smalled.spaceBetween()
                )
            )
            IconButton(
                onClick = { quantity.intValue = quantity.intValue + 1 }) {
                Icon(
                    modifier = Modifier.size(smalled.iconSize()),
                    painter = painterResource(id = R.drawable.home_quantity_plus_icon),
                    contentDescription = "add quantity"
                )
            }
        }
    }
}

private fun Boolean.height() = if (this) {
    36.dp
} else {
    48.dp
}

private fun Boolean.spaceBetween() = if (this) {
    8.dp
} else {
    20.dp
}

private fun Boolean.iconSize() = if (this) {
    16.dp
} else {
    28.dp
}

@Composable
private fun Boolean.fontStyle() = if (this) {
    MaterialTheme.typography.titleSmall
} else {
    MaterialTheme.typography.headlineLarge
}

private fun Boolean.paddingValues() = if (this) {
    PaddingValues(4.dp)
} else {
    PaddingValues(top = 12.dp, bottom = 12.dp, start = 20.dp, end = 20.dp)
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