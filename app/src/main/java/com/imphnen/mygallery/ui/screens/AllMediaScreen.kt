package com.imphnen.mygallery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.imphnen.mygallery.data.MediaItem
import com.imphnen.mygallery.data.SortOrder
import com.imphnen.mygallery.data.SortType
import com.imphnen.mygallery.ui.components.MediaGrid
import com.imphnen.mygallery.ui.components.SortMenu

/**
 * Screen showing all media items
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllMediaScreen(
    items: List<MediaItem>,
    sortType: SortType,
    sortOrder: SortOrder,
    onSortTypeChange: (SortType) -> Unit,
    onSortOrderChange: (SortOrder) -> Unit,
    onItemClick: (MediaItem) -> Unit,
    onFavoriteClick: (MediaItem) -> Unit,
    onDeleteClick: (MediaItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All Media") },
                actions = {
                    SortMenu(
                        currentSortType = sortType,
                        currentSortOrder = sortOrder,
                        onSortTypeChange = onSortTypeChange,
                        onSortOrderChange = onSortOrderChange
                    )
                }
            )
        }
    ) { padding ->
        MediaGrid(
            items = items.filterNot { it.isInTrash },
            onItemClick = onItemClick,
            onFavoriteClick = onFavoriteClick,
            onDeleteClick = onDeleteClick,
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}
