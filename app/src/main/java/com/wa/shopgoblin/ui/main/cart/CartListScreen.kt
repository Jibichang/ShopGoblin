package com.wa.shopgoblin.ui.main.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R
import com.wa.shopgoblin.ui.main.home.detail.AdjustQuantityButton
import com.wa.shopgoblin.ui.main.home.list.PlantImageCard

@Composable
fun CartList(
    modifier: Modifier = Modifier,
    carts: List<Cart> = emptyList()
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)) {
        item {
            Spacer(modifier = Modifier.size(1.dp))
        }
        this.items(items = carts) { cart ->
            Column {
                CartItem(cart)
            }
        }
        item {
            Spacer(modifier = Modifier.size(1.dp))
        }
    }
}
@Composable
fun CartItem(cart: Cart) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlantImageCard(modifier = Modifier.size(100.dp), icon = cart.icon)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = cart.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$${cart.price}",
                    style = MaterialTheme.typography.titleMedium
                )
                AdjustQuantityButton(
                    smalled = true,
                    quantity = remember { mutableIntStateOf(cart.quantity) }
                )
            }
            IconButton(modifier = Modifier.padding(top = 36.dp), onClick = {}) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.cart_delete_icon),
                    contentDescription = "delete"
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
//    CartItem(
//        Cart(
//            id = 1,
//            name = "test1",
//            icon = "plant_1",
//            price = 15.0,
//            quantity = 2
//        )
//    )
//    CartListScreen(
//        carts = cartList,
//        header = { CartHeader() },
//        footer = {
//            CheckoutTab()
//        }
//    )
    val contentPadding = PaddingValues(start = 24.dp, top = 24.dp, end = 24.dp)
    Column {
        CartHeader(modifier = Modifier.padding(contentPadding))
        CartList(
            modifier = Modifier
                .weight(1f),
            carts = cartList
        )
        CheckoutTab()
    }

//    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
//        this.items(items = cartList) { cart ->
//            Column {
//                CartItem(cart)
//            }
//        }
//    }
}

val cartList = listOf(
    Cart(
        id = 1,
        name = "test1",
        icon = "plant_1",
        price = 15.0,
        quantity = 2
    ),
    Cart(
        id = 2,
        name = "test2",
        icon = "plant_1",
        price = 14.0,
        quantity = 1
    ),
    Cart(
        id = 3,
        name = "test3",
        icon = "plant_1",
        price = 14.0,
        quantity = 1
    ),
    Cart(
        id = 4,
        name = "test4",
        icon = "plant_1",
        price = 14.0,
        quantity = 1
    ),
    Cart(
        id = 5,
        name = "test5",
        icon = "plant_1",
        price = 14.0,
        quantity = 5
    )
)