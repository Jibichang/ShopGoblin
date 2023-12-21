package com.wa.shopgoblin.ui.main.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R
import com.wa.shopgoblin.ui.main.home.detail.AddToCartButton
import com.wa.shopgoblin.ui.main.home.detail.TotalPriceTab
import com.wa.shopgoblin.ui.theme.ShopGoblinTheme

@Composable
fun CheckoutTab(
    enabled: Boolean = true,
    onClickCart: () -> Unit = {}
){
    val cornerShapeTop = RoundedCornerShape(
        topStart = 32.dp,
        topEnd = 32.dp
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = cornerShapeTop,
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        TotalPriceTab(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp), 123.0) {
            CheckoutButton(modifier = Modifier)
        }
    }
}

@Composable
fun CheckoutButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClickCart: () -> Unit = {}
) {
    val background = if (enabled) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }

    Surface(
        modifier = modifier
            .fillMaxWidth(),
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
            Text(text = stringResource(id = R.string.cart_checkout_button))
            Spacer(modifier = Modifier.size(16.dp))
            Icon(
                painter = painterResource(id = R.drawable.cart_checkout_icon),
                contentDescription = "Check Out"
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun CheckoutButtonPreview() {
//    CheckoutButton(
//        enabled = true
//    )
    ShopGoblinTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            ),
            shadowElevation = 8.dp,
            color = Color.White
        ) {
            TotalPriceTab(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp), 123.0) {
                CheckoutButton(modifier = Modifier)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AddCartButtonPreview() {
//    CheckoutButton(
//        enabled = true
//    )
    TotalPriceTab(price = 124.0) {
        AddToCartButton(enabled = true)
    }
}