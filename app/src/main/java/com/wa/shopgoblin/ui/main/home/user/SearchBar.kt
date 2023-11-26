package com.wa.shopgoblin.ui.main.home.user

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarScreen(
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        modifier = modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
        query = text,
        onQueryChange = {
            text = it
        },
        onSearch = {
            active = false
            onSearch()
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text(text = "Search", color = MaterialTheme.colorScheme.secondary)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.home_search_icon),
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.home_filter_icon),
                contentDescription = "Filter",
                tint = MaterialTheme.colorScheme.primary
            )
        }) { }
}