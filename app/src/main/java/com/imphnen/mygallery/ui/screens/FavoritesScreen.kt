package com.imphnen.mygallery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imphnen.mygallery.data.MediaItem
import com.imphnen.mygallery.ui.components.MediaGrid

/**
 * Screen showing favorite media items
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    items: List<MediaItem>,
    onItemClick: (MediaItem) -> Unit,
    onFavoriteClick: (MediaItem) -> Unit,
    onDeleteClick: (MediaItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorites") }
            )
        }
    ) { padding ->
        MediaGrid(
            items = items,
            onItemClick = onItemClick,
            onFavoriteClick = onFavoriteClick,
            onDeleteClick = onDeleteClick,
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}
