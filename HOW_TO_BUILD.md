# Build Guide - My Gallery

## 🚀 Cara Build Aplikasi

Karena project Android ini memerlukan Android Studio untuk build yang optimal, berikut adalah langkah-langkah yang direkomendasikan:

### ✅ **Method 1: Build dengan Android Studio** (RECOMMENDED)

#### Step 1: Buka Project

1. Launch **Android Studio**
2. Klik **File** → **Open**
3. Navigate ke folder: `D:\IMPHNEN\my-gallery`
4. Klik **OK**

#### Step 2: Wait for Gradle Sync

- Android Studio akan otomatis melakukan Gradle sync
- Tunggu sampai proses selesai (2-10 menit untuk pertama kali)
- Progress bisa dilihat di bottom bar

#### Step 3: Setup Icon (PENTING!)

Sebelum build, setup icon dulu:

1. Right-click folder `app/src/main/res`
2. Pilih **New** → **Image Asset**
3. Pilih **Launcher Icons (Adaptive and Legacy)**
4. Di tab "Foreground Layer":
   - Source: **Image**
   - Path: Browse ke `icon.jpg` di root folder
   - Trim: **Yes**
   - Resize: **75-80%**
5. Klik **Next** → **Finish**

#### Step 4: Build Debug APK

1. Klik menu **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
2. Tunggu proses build selesai
3. APK akan tersimpan di: `app/build/outputs/apk/debug/app-debug.apk`
4. Klik **locate** untuk buka folder

#### Step 5: Install & Test

1. **Install ke device**:
   - Connect Android device via USB (USB Debugging ON)
   - Atau start Android Emulator
2. **Run from Android Studio**:
   - Klik tombol **Run** (▶️) atau tekan `Shift + F10`
   - Pilih device target
   - App akan otomatis terinstall dan launch

### ⚙️ **Method 2: Build via Command Line** (Advanced)

Jika Gradle wrapper sudah di-setup:

```powershell
# Navigate to project folder
cd D:\IMPHNEN\my-gallery

# Build Debug APK
.\gradlew.bat assembleDebug

# Build Release APK (unsigned)
.\gradlew.bat assembleRelease
```

**Note**: Method ini memerlukan gradle wrapper yang sudah di-setup. Jika belum ada, gunakan Method 1 dengan Android Studio.

## 📱 Output APK Locations

### Debug APK:

```
app\build\outputs\apk\debug\app-debug.apk
```

- **Size**: ~10-20 MB
- **Debuggable**: Yes
- **Ready for**: Testing

### Release APK:

```
app\build\outputs\apk\release\app-release-unsigned.apk
```

- **Size**: Lebih kecil (setelah ProGuard)
- **Debuggable**: No
- **Needs**: Signing untuk distribusi

## ⚠️ Troubleshooting

### Problem: Gradle Sync Failed

**Solution:**

1. Click **File** → **Invalidate Caches** → **Restart**
2. Check internet connection
3. Wait dan retry

### Problem: SDK Not Found

**Solution:**

- File `local.properties` sudah dibuat dengan path:
  `C:\Users\CASTORICE\AppData\Local\Android\Sdk`
- Jika masih error, verify path di SDK Manager

### Problem: Build Error

**Solution:**

1. Clean project: **Build** → **Clean Project**
2. Rebuild: **Build** → **Rebuild Project**
3. Check error messages di "Build" tab

### Problem: Icon Not Found

**Solution:**

- Follow Step 3 di atas untuk setup icon
- Icon source: `icon.jpg` di root folder

## ✅ Build Success Indicators

Jika build berhasil, Anda akan melihat:

- ✅ Message: "BUILD SUCCESSFUL in Xs"
- ✅ APK file muncul di folder output
- ✅ No error messages di Build tab

## 🎯 Quick Start Commands

### Open Project in Android Studio:

```
Path: D:\IMPHNEN\my-gallery
```

### Verify local.properties exists:

```powershell
Get-Content D:\IMPHNEN\my-gallery\local.properties
```

### Check if gradlew exists:

```powershell
Test-Path D:\IMPHNEN\my-gallery\gradlew.bat
```

## 📝 Post-Build Steps

Setelah build berhasil:

1. **Test APK**:

   - Install `app-debug.apk` ke device
   - Grant storage permissions
   - Verify semua fitur berfungsi

2. **Create Release** (Optional):
   - Sign APK dengan keystore
   - Upload ke GitHub Releases
   - Share dengan users

## 🚀 Next Steps

Setelah build berhasil:

- [ ] Test di multiple devices
- [ ] Take screenshots untuk README
- [ ] Build release APK
- [ ] Upload ke GitHub
- [ ] Create release dengan APK

---

**Recommended Action**: Buka project di Android Studio dan ikuti Method 1 untuk build yang paling mudah dan reliable.

**File yang sudah ready**:

- ✅ Source code lengkap (13 Kotlin files)
- ✅ Build configurations
- ✅ local.properties (SDK path configured)
- ✅ Icon file (icon.jpg)
- ✅ All resources dan manifests

**Tinggal**:

1. Open di Android Studio
2. Setup icon dengan Image Asset Studio
3. Click Build → Build APK
4. Selesai! 🎉
