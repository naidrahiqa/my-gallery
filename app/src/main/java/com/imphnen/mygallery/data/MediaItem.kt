package com.imphnen.mygallery.data

import android.net.Uri

/**
 * Data class representing a media item (image or video)
 */
data class MediaItem(
    val id: Long,
    val uri: Uri,
    val name: String,
    val dateAdded: Long,
    val size: Long,
    val duration: Long = 0, // For videos, in milliseconds
    val mimeType: String,
    val folderName: String,
    val isFavorite: Boolean = false,
    val isInTrash: Boolean = false
) {
    val isVideo: Boolean
        get() = mimeType.startsWith("video/")
    
    val isImage: Boolean
        get() = mimeType.startsWith("image/")
}

/**
 * Enum class for sorting options
 */
enum class SortType {
    TITLE,
    DATE,
    DURATION,
    SIZE
}

/**
 * Enum class for sort order
 */
enum class SortOrder {
    ASCENDING,  // Oldest first
    DESCENDING  // Newest first
}

/**
 * Data class for folder grouping
 */
data class MediaFolder(
    val name: String,
    val items: List<MediaItem>,
    val thumbnailUri: Uri?
) {
    val itemCount: Int
        get() = items.size
}
