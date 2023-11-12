package com.wa.shopgoblin.ui.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R
import com.wa.shopgoblin.ui.theme.ShopGoblinTheme

val tabHeight = 56.dp

@Composable
fun UserStatusBar(
    modifier: Modifier = Modifier,
    userName: String,
    onNotificationClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(tabHeight)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.home_user_icon),
            contentDescription = "Profile Image"
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
                contentDescription = Notification.route
            )
        }
        Spacer(modifier.size(16.dp))
        IconButton(
            modifier = modifier.size(24.dp),
            onClick = onFavoriteClick
        ) {
            Icon(
                painter = painterResource(id = Favorite.icon),
                contentDescription = Favorite.route,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}