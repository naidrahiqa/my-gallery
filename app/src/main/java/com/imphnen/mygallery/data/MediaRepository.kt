package com.imphnen.mygallery.data

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for accessing media files from device storage
 */
class MediaRepository(private val contentResolver: ContentResolver) {
    
    private val favorites = mutableSetOf<Long>()
    private val trashItems = mutableSetOf<Long>()
    
    /**
     * Load all media items (images and videos) from device storage
     */
    suspend fun loadMediaItems(): List<MediaItem> = withContext(Dispatchers.IO) {
        val mediaItems = mutableListOf<MediaItem>()
        
        // Load images
        mediaItems.addAll(loadImages())
        
        // Load videos
        mediaItems.addAll(loadVideos())
        
        mediaItems
    }
    
    /**
     * Load image files
     */
    private fun loadImages(): List<MediaItem> {
        val images = mutableListOf<MediaItem>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )
        
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
        
        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val mimeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
            val folderColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val dateAdded = cursor.getLong(dateColumn)
                val size = cursor.getLong(sizeColumn)
                val mimeType = cursor.getString(mimeColumn)
                val folder = cursor.getString(folderColumn) ?: "Unknown"
                
                val uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                
                images.add(
                    MediaItem(
                        id = id,
                        uri = uri,
                        name = name,
                        dateAdded = dateAdded,
                        size = size,
                        mimeType = mimeType,
                        folderName = folder,
                        isFavorite = favorites.contains(id),
                        isInTrash = trashItems.contains(id)
                    )
                )
            }
        }
        
        return images
    }
    
    /**
     * Load video files
     */
    private fun loadVideos(): List<MediaItem> {
        val videos = mutableListOf<MediaItem>()
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME
        )
        
        val sortOrder = "${MediaStore.Video.Media.DATE_ADDED} DESC"
        
        contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val dateColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val mimeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)
            val folderColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
            
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val dateAdded = cursor.getLong(dateColumn)
                val size = cursor.getLong(sizeColumn)
                val duration = cursor.getLong(durationColumn)
                val mimeType = cursor.getString(mimeColumn)
                val folder = cursor.getString(folderColumn) ?: "Unknown"
                
                val uri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                
                videos.add(
                    MediaItem(
                        id = id,
                        uri = uri,
                        name = name,
                        dateAdded = dateAdded,
                        size = size,
                        duration = duration,
                        mimeType = mimeType,
                        folderName = folder,
                        isFavorite = favorites.contains(id),
                        isInTrash = trashItems.contains(id)
                    )
                )
            }
        }
        
        return videos
    }
    
    /**
     * Toggle favorite status
     */
    fun toggleFavorite(itemId: Long) {
        if (favorites.contains(itemId)) {
            favorites.remove(itemId)
        } else {
            favorites.add(itemId)
        }
    }
    
    /**
     * Move to trash
     */
    fun moveToTrash(itemId: Long) {
        trashItems.add(itemId)
    }
    
    /**
     * Restore from trash
     */
    fun restoreFromTrash(itemId: Long) {
        trashItems.remove(itemId)
    }
    
    /**
     * Check if item is favorite
     */
    fun isFavorite(itemId: Long): Boolean = favorites.contains(itemId)
    
    /**
     * Check if item is in trash
     */
    fun isInTrash(itemId: Long): Boolean = trashItems.contains(itemId)
}
