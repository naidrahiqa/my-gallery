package com.imphnen.mygallery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imphnen.mygallery.data.MediaItem
import com.imphnen.mygallery.ui.components.MediaGrid

/**
 * Screen showing items in trash
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrashScreen(
    items: List<MediaItem>,
    onItemClick: (MediaItem) -> Unit,
    onRestoreClick: (MediaItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Trash") }
            )
        }
    ) { padding ->
        MediaGrid(
            items = items,
            onItemClick = onItemClick,
            onFavoriteClick = onRestoreClick, // Reuse favorite button for restore
            onDeleteClick = {}, // No delete in trash
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}
