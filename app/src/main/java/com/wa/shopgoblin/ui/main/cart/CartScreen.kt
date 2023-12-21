package com.wa.shopgoblin.ui.main.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R

@Composable
fun CartScreen(
    carts: List<Cart> = emptyList(),
    onClick: (String) -> Unit = {}
) {
    val contentPadding = PaddingValues(start = 24.dp, top = 24.dp, end = 24.dp)
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        if (carts.isEmpty()) {
            CartEmptyScreen {
                CartHeader()
            }
        } else {
            Column {
                CartHeader(modifier = Modifier.padding(contentPadding))
                CartList(
                    modifier = Modifier
                        .weight(1f),
                    carts = cartList
                )
                CheckoutTab()
            }
        }
    }
}

@Composable
fun CartHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            painter = painterResource(id = R.drawable.cart_header_logo),
            contentDescription = "logo",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(id = R.string.cart_header),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun CardHeaderPrev() {
    CartHeader()
}