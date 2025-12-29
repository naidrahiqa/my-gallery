# My Gallery - Project Summary

## 📱 Aplikasi

**My Gallery** adalah aplikasi galeri Android modern yang dibangun dengan Kotlin dan Jetpack Compose.

## ✅ Fitur yang Telah Diimplementasikan

### 1. **Tampilan Media**

- ✅ **All Media Screen**: Lihat semua gambar dan video dalam grid layout
- ✅ **Folders Screen**: Media dikelompokkan berdasarkan folder
- ✅ **Favorites Screen**: Koleksi media favorit Anda
- ✅ **Trash Screen**: Media yang dihapus dengan opsi restore

### 2. **Fitur Sorting Lengkap**

- ✅ Sort berdasarkan **Judul/Title** (A-Z)
- ✅ Sort berdasarkan **Tanggal/Date** (kapan file ditambahkan)
- ✅ Sort berdasarkan **Durasi/Duration** (untuk video)
- ✅ Sort berdasarkan **Ukuran/Size** (besar file)
- ✅ Sort Order: **Terbaru dulu** (Descending) atau **Terlama dulu** (Ascending)

### 3. **Manajemen Media**

- ✅ Toggle Favorite/Unfavorite
- ✅ Move to Trash
- ✅ Restore from Trash
- ✅ Auto-hide trash items dari view utama

### 4. **UI/UX**

- ✅ Material Design 3
- ✅ Bottom Navigation Bar
- ✅ Smooth animations
- ✅ Indicator untuk video (play icon)
- ✅ Indicator untuk favorites (heart icon)
- ✅ Empty state messages

## 🏗️ Arsitektur

### **Pattern: MVVM (Model-View-ViewModel)**

```
├── Data Layer (Model)
│   ├── MediaItem.kt - Data class untuk media
│   ├── MediaRepository.kt - Akses MediaStore
│   └── Enums untuk SortType & SortOrder
│
├── ViewModel Layer
│   └── GalleryViewModel.kt - Business logic & state management
│
└── UI Layer (View)
    ├── Screens (AllMedia, Folders, Favorites, Trash)
    ├── Components (MediaGrid, SortMenu)
    └── Theme (Colors, Typography)
```

### **Teknologi Stack**

- **Language**: Kotlin 100%
- **UI**: Jetpack Compose + Material 3
- **Navigation**: Navigation Compose
- **Image Loading**: Coil (untuk gambar & video thumbnails)
- **Architecture**: MVVM + StateFlow
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 📁 Struktur File Project

```
my-gallery/
├── app/
│   ├── src/main/
│   │   ├── java/com/imphnen/mygallery/
│   │   │   ├── data/
│   │   │   │   ├── MediaItem.kt
│   │   │   │   └── MediaRepository.kt
│   │   │   ├── viewmodel/
│   │   │   │   └── GalleryViewModel.kt
│   │   │   ├── ui/
│   │   │   │   ├── components/
│   │   │   │   │   ├── MediaGrid.kt
│   │   │   │   │   └── SortMenu.kt
│   │   │   │   ├── screens/
│   │   │   │   │   ├── AllMediaScreen.kt
│   │   │   │   │   ├── FoldersScreen.kt
│   │   │   │   │   ├── FavoritesScreen.kt
│   │   │   │   │   └── TrashScreen.kt
│   │   │   │   └── theme/
│   │   │   │       ├── Color.kt
│   │   │   │       ├── Theme.kt
│   │   │   │       └── Type.kt
│   │   │   └── MainActivity.kt
│   │   ├── res/
│   │   │   ├── values/
│   │   │   │   ├── strings.xml
│   │   │   │   └── themes.xml
│   │   │   └── xml/
│   │   │       ├── backup_rules.xml
│   │   │       └── data_extraction_rules.xml
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── .gitignore
├── README.md
├── LICENSE
├── CHANGELOG.md
├── CONTRIBUTING.md
├── ICON_SETUP.md
├── REPOSITORY_INFO.md
└── icon.jpg (App icon - sudah disediakan)
```

## 🎯 Cara Kerja Aplikasi

### 1. **Media Loading**

- App menggunakan MediaStore API untuk scan gambar & video
- Loading dilakukan asynchronously (tidak block UI)
- Mendukung Android 13+ scoped storage

### 2. **Sorting System**

```kotlin
- Title: Sort alphabetically (case-insensitive)
- Date: Sort by dateAdded timestamp
- Duration: Sort by video duration (ms)
- Size: Sort by file size (bytes)
- Order: Reverse list untuk descending
```

### 3. **State Management**

- Menggunakan StateFlow untuk reactive updates
- ViewModel maintain single source of truth
- UI otomatis update saat state berubah

### 4. **Permission Handling**

- Request runtime permissions
- Support Android 13+ granular permissions
- Fallback untuk Android 12 ke bawah

## 📝 Dokumentasi

### File Dokumentasi yang Tersedia:

1. **README.md** - Dokumentasi utama & getting started
2. **REPOSITORY_INFO.md** - Deskripsi untuk GitHub repository
3. **CONTRIBUTING.md** - Guidelines untuk kontributor
4. **CHANGELOG.md** - Version history
5. **ICON_SETUP.md** - Cara setup app icon
6. **LICENSE** - MIT License

## 🚀 Cara Build & Run

### Prerequisites:

- Android Studio Hedgehog (2023.1.1) atau lebih baru
- JDK 8+
- Android SDK dengan API 34

### Steps:

1. Buka project di Android Studio
2. Wait for Gradle sync
3. Connect device atau start emulator
4. Click Run (Shift + F10)
5. Grant storage permissions saat diminta

### Build APK:

```bash
# Debug APK
./gradlew assembleDebug

# Release APK
./gradlew assembleRelease
```

## 🎨 Icon App

- Icon sudah disediakan: `icon.jpg`
- Lihat `ICON_SETUP.md` untuk instruksi setup di Android Studio
- Gunakan Image Asset Studio untuk generate berbagai ukuran

## 📊 Repository Info untuk GitHub

### Short Description:

```
Simple and elegant Android gallery app built with Kotlin & Jetpack Compose.
Features: favorites, trash, folder view, and multiple sorting options.
```

### Tags:

```
android, kotlin, jetpack-compose, gallery-app, material-design,
media-player, photo-gallery, video-gallery, mvvm-architecture,
coil, navigation-compose
```

## 🔮 Future Enhancements (Belum Diimplementasi)

- Full-screen media viewer
- Video playback dengan ExoPlayer
- Share functionality
- Persistent storage (Room database)
- Search functionality
- Batch operations
- Export/Import favorites

## 💡 Tips untuk Development

1. **Testing di Device**:

   - Test dengan berbagai versi Android
   - Pastikan ada media di device untuk testing
   - Test permission flow di Android 13+

2. **Icon Setup**:

   - Ikuti instruksi di ICON_SETUP.md
   - Gunakan Android Asset Studio

3. **Git Workflow**:
   - File .gitignore sudah dikonfigurasi
   - Exclude build files dan local.properties

## ✨ Highlights

### Kode Berkualitas:

- ✅ 100% Kotlin
- ✅ MVVM Architecture
- ✅ Compose best practices
- ✅ Material Design 3
- ✅ Responsive UI
- ✅ Efficient image loading

### Dokumentasi Lengkap:

- ✅ Comprehensive README
- ✅ Code comments
- ✅ Repository description
- ✅ Contributing guidelines
- ✅ Changelog
- ✅ License (MIT)

---

## 🎓 Cocok Untuk:

- Portfolio project
- Learning Jetpack Compose
- Reference untuk MediaStore API
- MVVM architecture example
- Material Design 3 showcase

**Created with ❤️ by IMPHNEN**
**Built with Kotlin & Jetpack Compose**
