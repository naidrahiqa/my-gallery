# 🚀 Quick Start Guide - My Gallery

## Langkah Cepat untuk Mulai

### 1️⃣ Setup Icon (Penting!)

Icon app sudah tersedia (`icon.jpg`), tapi perlu di-convert ke format Android:

**Cara Termudah:**

1. Buka Android Studio
2. Klik kanan pada folder `app/src/main/res`
3. Pilih `New` → `Image Asset`
4. Pilih `Launcher Icons (Adaptive and Legacy)`
5. Di tab "Foreground Layer":
   - Path type: Image
   - Path: Browse ke `icon.jpg` di root folder
6. Klik `Next` → `Finish`

### 2️⃣ Build Project

```bash
# Di Android Studio:
1. File → Open → Pilih folder "my-gallery"
2. Tunggu Gradle sync selesai (bisa 2-5 menit pertama kali)
3. Jika ada error sync, coba: File → Invalidate Caches → Restart
```

### 3️⃣ Run Aplikasi

```bash
# Dengan Device/Emulator:
1. Connect Android device (USB Debugging ON) ATAU
2. Start Android Emulator dari AVD Manager
3. Click tombol Run (▶️) atau tekan Shift + F10
4. Pilih device target
5. Tunggu build selesai
```

### 4️⃣ Testing

Saat app pertama kali dibuka:

1. App akan minta storage permission → Klik "Grant Permission" → Allow
2. App akan load semua foto & video dari device
3. Coba navigasi ke tab Folders, Favorites, Trash
4. Test fitur sorting dengan icon Sort di kanan atas

### 5️⃣ Build APK untuk Distribusi

```bash
# Debug APK (untuk testing):
./gradlew assembleDebug
# Output: app/build/outputs/apk/debug/app-debug.apk

# Release APK (untuk production):
./gradlew assembleRelease
# Output: app/build/outputs/apk/release/app-release.apk
```

## ⚠️ Troubleshooting

### Build Error: "SDK not found"

**Solusi:**

```bash
# Buat file local.properties di root project:
sdk.dir=C\:\\Users\\<USERNAME>\\AppData\\Local\\Android\\Sdk
# Sesuaikan path dengan lokasi Android SDK Anda
```

### Permission Denied di Device

**Solusi:**

1. Go to Settings → Apps → My Gallery → Permissions
2. Enable "Photos and videos" atau "Storage"

### Icon tidak muncul

**Solusi:**

1. Ikuti langkah Setup Icon di atas
2. Clean project: Build → Clean Project
3. Rebuild: Build → Rebuild Project

### Gradle Sync Failed

**Solusi:**

1. Check internet connection
2. File → Invalidate Caches → Restart
3. Pastikan menggunakan Android Studio Hedgehog atau lebih baru

## 📱 Minimum Requirements

- **Device**: Android 7.0+ (API 24)
- **Development**:
  - Android Studio Hedgehog (2023.1.1) atau lebih baru
  - JDK 8+
  - Minimum 4GB RAM (8GB recommended)
  - 2GB free disk space

## 🎯 What to Test

### Functional Testing:

- ✅ Permission request flow
- ✅ Loading media dari storage
- ✅ Navigation antar tabs (All Media, Folders, Favorites, Trash)
- ✅ Add/Remove favorites
- ✅ Move to trash & restore
- ✅ Sorting by Title, Date, Duration, Size
- ✅ Toggle sort order (Newest/Oldest)

### UI Testing:

- ✅ Grid layout responsive
- ✅ Icons (favorite heart, video play) tampil dengan benar
- ✅ Empty states (no media, no favorites, etc.)
- ✅ Smooth scrolling
- ✅ Bottom navigation responsive

## 📝 Next Steps

### Untuk Development:

1. Baca `CONTRIBUTING.md` untuk guidelines
2. Check `PROJECT_SUMMARY.md` untuk architecture details
3. Explore code di `app/src/main/java/com/imphnen/mygallery/`

### Untuk Deployment:

1. Sign APK dengan keystore (untuk Google Play)
2. Test di berbagai devices & Android versions
3. Buat screenshots untuk Play Store listing
4. Update version di `app/build.gradle.kts`

## 🆘 Need Help?

- Check issue tracker di GitHub
- Review dokumentasi di README.md
- Lihat code comments untuk detail implementation

---

**Ready to explore? Happy coding! 🎉**
