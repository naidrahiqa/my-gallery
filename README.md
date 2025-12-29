# My Gallery 📸

A simple and elegant gallery application for Android built with Kotlin and Jetpack Compose. Browse, organize, and manage your photos and videos with ease.

![My Gallery](icon.jpg)

## ✨ Features

### 📱 Core Features

- **All Media View**: Browse all your photos and videos in one place
- **Folder Organization**: View media organized by folders
- **Favorites**: Mark your favorite photos and videos for quick access
- **Trash**: Safely delete items with a trash system and restore them if needed

### 🔧 Advanced Features

- **Multiple Sorting Options**:
  - Sort by Title (A-Z)
  - Sort by Date (newest/oldest first)
  - Sort by Duration (for videos)
  - Sort by File Size
- **Flexible Sort Order**: Switch between ascending (oldest first) and descending (newest first)
- **Modern UI**: Built with Material Design 3 and Jetpack Compose
- **Efficient Image Loading**: Uses Coil for fast and smooth image/video thumbnails
- **Permission Handling**: Seamless runtime permission requests for Android 13+ (Tiramisu)

## 🛠️ Technical Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material 3)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Image Loading**: Coil
- **Navigation**: Jetpack Navigation Compose
- **Minimum SDK**: Android 7.0 (API 24)
- **Target SDK**: Android 14 (API 34)

## 📦 Dependencies

```kotlin
// Core
androidx.core:core-ktx:1.12.0
androidx.lifecycle:lifecycle-runtime-ktx:2.6.2

// Compose
androidx.compose.ui
androidx.compose.material3
androidx.compose.material:material-icons-extended

// Navigation
androidx.navigation:navigation-compose:2.7.5

// Image Loading
io.coil-kt:coil-compose:2.5.0
io.coil-kt:coil-video:2.5.0

// ViewModel
androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2

// Permissions
com.google.accompanist:accompanist-permissions:0.32.0
```

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 8 or later
- Android SDK with API 34

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/my-gallery.git
   cd my-gallery
   ```

2. **Open in Android Studio**

   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory
   - Wait for Gradle sync to complete

3. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button or press `Shift + F10`
   - Grant storage permissions when prompted

## 📱 Permissions

The app requires the following permissions:

- **Android 13+ (API 33+)**:

  - `READ_MEDIA_IMAGES` - To access photos
  - `READ_MEDIA_VIDEO` - To access videos

- **Android 12 and below**:
  - `READ_EXTERNAL_STORAGE` - To access all media files

## 🎨 Screenshots

_Screenshots coming soon!_

## 📂 Project Structure

```
my-gallery/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/imphnen/mygallery/
│   │   │   │   ├── data/
│   │   │   │   │   ├── MediaItem.kt          # Data models
│   │   │   │   │   └── MediaRepository.kt    # Media access layer
│   │   │   │   ├── viewmodel/
│   │   │   │   │   └── GalleryViewModel.kt   # Business logic
│   │   │   │   ├── ui/
│   │   │   │   │   ├── components/
│   │   │   │   │   │   ├── MediaGrid.kt      # Reusable grid component
│   │   │   │   │   │   └── SortMenu.kt       # Sort options menu
│   │   │   │   │   ├── screens/
│   │   │   │   │   │   ├── AllMediaScreen.kt
│   │   │   │   │   │   ├── FoldersScreen.kt
│   │   │   │   │   │   ├── FavoritesScreen.kt
│   │   │   │   │   │   └── TrashScreen.kt
│   │   │   │   │   └── theme/
│   │   │   │   │       ├── Color.kt
│   │   │   │   │       ├── Theme.kt
│   │   │   │   │       └── Type.kt
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle.kts
│   └── proguard-rules.pro
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## 🔄 How It Works

### Media Loading

The app scans your device's MediaStore for images and videos, respecting Android's scoped storage guidelines. Media items are loaded asynchronously to ensure smooth performance.

### Sorting Algorithm

Media items can be sorted by various criteria:

- **Title**: Alphabetical sorting (case-insensitive)
- **Date**: Based on when the file was added to the device
- **Duration**: Sorts videos by their length (images treated as 0 duration)
- **Size**: Sorts by file size in bytes

### Favorites & Trash

- Favorites and trash states are maintained in-memory
- Items in trash are hidden from the main views
- Items can be restored from trash
- _Note: In the current version, these states are not persisted across app restarts_

## 🚧 Future Enhancements

- [ ] Full-screen media viewer
- [ ] Video playback
- [ ] Share functionality
- [ ] Persistent favorites and trash (using Room database)
- [ ] Search functionality
- [ ] Batch selection and operations
- [ ] Slideshow mode
- [ ] Dark/Light theme toggle
- [ ] Export/Import favorites list

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

**IMPHNEN**

## 🙏 Acknowledgments

- Built with [Jetpack Compose](https://developer.android.com/jetpack/compose)
- Icons from [Material Design Icons](https://fonts.google.com/icons)
- Image loading by [Coil](https://coil-kt.github.io/coil/)

---

Made with ❤️ using Kotlin and Jetpack Compose
