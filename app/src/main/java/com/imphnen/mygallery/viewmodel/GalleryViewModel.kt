package com.imphnen.mygallery.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.imphnen.mygallery.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing gallery state
 */
class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository = MediaRepository(application.contentResolver)
    
    private val _allMedia = MutableStateFlow<List<MediaItem>>(emptyList())
    val allMedia: StateFlow<List<MediaItem>> = _allMedia.asStateFlow()
    
    private val _folders = MutableStateFlow<List<MediaFolder>>(emptyList())
    val folders: StateFlow<List<MediaFolder>> = _folders.asStateFlow()
    
    private val _sortType = MutableStateFlow(SortType.DATE)
    val sortType: StateFlow<SortType> = _sortType.asStateFlow()
    
    private val _sortOrder = MutableStateFlow(SortOrder.DESCENDING)
    val sortOrder: StateFlow<SortOrder> = _sortOrder.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    /**
     * Load all media from device
     */
    fun loadMedia() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val items = repository.loadMediaItems()
                _allMedia.value = sortMediaItems(items)
                updateFolders(items)
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Update folders from media items
     */
    private fun updateFolders(items: List<MediaItem>) {
        val folderMap = items
            .filterNot { it.isInTrash }
            .groupBy { it.folderName }
        
        _folders.value = folderMap.map { (name, mediaItems) ->
            MediaFolder(
                name = name,
                items = sortMediaItems(mediaItems),
                thumbnailUri = mediaItems.firstOrNull()?.uri
            )
        }.sortedBy { it.name }
    }
    
    /**
     * Get favorite items
     */
    fun getFavorites(): List<MediaItem> {
        return _allMedia.value.filter { it.isFavorite && !it.isInTrash }
    }
    
    /**
     * Get trash items
     */
    fun getTrashItems(): List<MediaItem> {
        return _allMedia.value.filter { it.isInTrash }
    }
    
    /**
     * Toggle favorite status
     */
    fun toggleFavorite(item: MediaItem) {
        repository.toggleFavorite(item.id)
        loadMedia() // Reload to update UI
    }
    
    /**
     * Move item to trash
     */
    fun moveToTrash(item: MediaItem) {
        repository.moveToTrash(item.id)
        loadMedia() // Reload to update UI
    }
    
    /**
     * Restore item from trash
     */
    fun restoreFromTrash(item: MediaItem) {
        repository.restoreFromTrash(item.id)
        loadMedia() // Reload to update UI
    }
    
    /**
     * Update sort type
     */
    fun setSortType(type: SortType) {
        _sortType.value = type
        _allMedia.value = sortMediaItems(_allMedia.value)
        updateFolders(_allMedia.value)
    }
    
    /**
     * Update sort order
     */
    fun setSortOrder(order: SortOrder) {
        _sortOrder.value = order
        _allMedia.value = sortMediaItems(_allMedia.value)
        updateFolders(_allMedia.value)
    }
    
    /**
     * Sort media items based on current sort type and order
     */
    private fun sortMediaItems(items: List<MediaItem>): List<MediaItem> {
        val sorted = when (_sortType.value) {
            SortType.TITLE -> items.sortedBy { it.name.lowercase() }
            SortType.DATE -> items.sortedBy { it.dateAdded }
            SortType.DURATION -> items.sortedBy { it.duration }
            SortType.SIZE -> items.sortedBy { it.size }
        }
        
        return if (_sortOrder.value == SortOrder.DESCENDING) {
            sorted.reversed()
        } else {
            sorted
        }
    }
    
    /**
     * Get items for specific folder
     */
    fun getItemsForFolder(folderName: String): List<MediaItem> {
        return _allMedia.value.filter { it.folderName == folderName && !it.isInTrash }
    }
}
