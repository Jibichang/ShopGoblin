package com.wa.shopgoblin.ui.main.home.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R
import com.wa.shopgoblin.data.database.plant.Plant
import com.wa.shopgoblin.ui.main.home.user.FavoriteIcon
import com.wa.shopgoblin.ui.main.home.list.RatingScoreMinimize

@Composable
fun DescriptionSection(
    description: String
) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource(id = R.string.home_detail_description_title),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun TitleAndRatingSection(
    plant: Plant,
    onFavoriteClick: (Plant, Boolean) -> Unit = { _, _ -> }
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = plant.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(12.dp))
            IconButton(onClick = {
                val checked = !plant.favorite
                onFavoriteClick(plant, checked)
            }) {
                FavoriteIcon(
                    modifier = Modifier.size(28.dp),
                    checked = plant.favorite
                )
            }
        }
        Spacer(modifier = Modifier.size(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            RatingScoreMinimize(rating = plant.rating, modifier = Modifier.size(4.dp))
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "(${plant.reviewer} reviews)",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}