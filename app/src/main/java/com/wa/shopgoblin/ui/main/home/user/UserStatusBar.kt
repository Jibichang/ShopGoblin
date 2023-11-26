package com.wa.shopgoblin.ui.main.home.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R
import com.wa.shopgoblin.ui.main.Favorite
import com.wa.shopgoblin.ui.main.Notification
import com.wa.shopgoblin.ui.theme.ShopGoblinTheme

@Composable
fun UserStatusBar(
    modifier: Modifier = Modifier,
    userName: String,
    onNotificationClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.home_user_icon),
                contentDescription = "Profile Image",
                modifier = Modifier.size(48.dp)
            )

            Row(modifier = modifier.weight(1f)) {
                Spacer(modifier.size(16.dp))
                Column {
                    Text(text = "Hello!")
                    Text(
                        text = userName,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(modifier.size(16.dp))
            IconButton(
                modifier = modifier.size(24.dp),
                onClick = onNotificationClick
            ) {
                Icon(
                    painter = painterResource(id = Notification.icon),
                    contentDescription = Notification.route,
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier.size(16.dp))
            IconButton(
                modifier = modifier.size(24.dp),
                onClick = onFavoriteClick
            ) {
                FavoriteIcon(modifier = Modifier.size(28.dp))
            }
        }
    }
}

@Preview
@Composable
fun UserPrev() {
    ShopGoblinTheme {
//        FavoriteIcon(painterResource(id = R.drawable.home_favourited_icon))
    }
}

@Composable
fun FavoriteIcon(
    modifier: Modifier = Modifier,
    checked: Boolean = false
) {
    val painter = if (checked) {
        painterResource(id = R.drawable.home_favorited_icon)
    } else {
        painterResource(id = R.drawable.home_favorite_icon)
    }
    Icon(
        modifier = modifier,
        painter = painter,
        contentDescription = Favorite.route,
        tint = MaterialTheme.colorScheme.primary
    )
}