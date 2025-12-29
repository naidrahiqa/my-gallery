package com.imphnen.mygallery.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imphnen.mygallery.data.SortOrder
import com.imphnen.mygallery.data.SortType

/**
 * Sort menu dropdown
 */
@Composable
fun SortMenu(
    currentSortType: SortType,
    currentSortOrder: SortOrder,
    onSortTypeChange: (SortType) -> Unit,
    onSortOrderChange: (SortOrder) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    
    Box(modifier = modifier) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Filled.Sort,
                contentDescription = "Sort"
            )
        }
        
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Text(
                text = "Sort by",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            Divider()
            
            // Sort type options
            SortType.values().forEach { type ->
                DropdownMenuItem(
                    text = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(getSortTypeLabel(type))
                            if (currentSortType == type) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    },
                    onClick = {
                        onSortTypeChange(type)
                    }
                )
            }
            
            Divider()
            
            // Sort order options
            DropdownMenuItem(
                text = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Newest First")
                        if (currentSortOrder == SortOrder.DESCENDING) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                onClick = {
                    onSortOrderChange(SortOrder.DESCENDING)
                }
            )
            
            DropdownMenuItem(
                text = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Oldest First")
                        if (currentSortOrder == SortOrder.ASCENDING) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                onClick = {
                    onSortOrderChange(SortOrder.ASCENDING)
                }
            )
        }
    }
}

private fun getSortTypeLabel(type: SortType): String {
    return when (type) {
        SortType.TITLE -> "Title"
        SortType.DATE -> "Date"
        SortType.DURATION -> "Duration"
        SortType.SIZE -> "Size"
    }
}
