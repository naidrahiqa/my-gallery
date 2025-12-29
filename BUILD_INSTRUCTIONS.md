# Build Instructions - My Gallery

## 🏗️ Building the Project

### Prerequisites Checklist

- [ ] Android Studio Hedgehog (2023.1.1) atau lebih baru
- [ ] JDK 8 atau lebih baru (JDK 11 recommended)
- [ ] Android SDK dengan API Level 34
- [ ] Minimum 4GB RAM (8GB recommended)
- [ ] Internet connection untuk download dependencies

## Step-by-Step Build Process

### 1. Initial Setup

#### Clone Repository

```bash
git clone https://github.com/yourusername/my-gallery.git
cd my-gallery
```

#### Configure SDK Path

Buat file `local.properties` di root folder (jika belum ada):

```properties
sdk.dir=C\:\\Users\\<YOUR_USERNAME>\\AppData\\Local\\Android\\Sdk
```

> **Note**: Path bisa berbeda tergantung OS:
>
> - **Windows**: `C:\\Users\\<USERNAME>\\AppData\\Local\\Android\\Sdk`
> - **Mac**: `/Users/<USERNAME>/Library/Android/sdk`
> - **Linux**: `/home/<USERNAME>/Android/Sdk`

### 2. Open in Android Studio

1. Launch Android Studio
2. Select "Open an Existing Project"
3. Navigate to `my-gallery` folder
4. Click "OK"
5. Wait for Gradle sync (2-5 minutes pada first run)

### 3. Resolve Dependencies

Gradle akan otomatis download semua dependencies. Jika gagal:

```bash
# From terminal in Android Studio:
./gradlew clean
./gradlew build --refresh-dependencies
```

### 4. Setup App Icon

**⚠️ IMPORTANT**: Icon harus di-setup sebelum build!

1. Right-click `app/src/main/res`
2. `New` → `Image Asset`
3. Select `Launcher Icons (Adaptive and Legacy)`
4. Foreground Layer:
   - Source: Image
   - Path: Browse to `icon.jpg` in root folder
   - Trim: Yes
   - Resize: 75-80%
5. Background Layer:
   - Use default or customize
6. Click `Next` → `Finish`

### 5. Build Variants

#### Debug Build (Development)

```bash
# Command line:
./gradlew assembleDebug

# Output location:
app/build/outputs/apk/debug/app-debug.apk
```

**Kegunaan**: Testing, debugging, development

#### Release Build (Production)

```bash
# Command line:
./gradlew assembleRelease

# Output location:
app/build/outputs/apk/release/app-release-unsigned.apk
```

**Note**: Release APK harus di-sign untuk distribusi!

### 6. Sign Release APK

#### Option A: Using Android Studio

1. `Build` → `Generate Signed Bundle / APK`
2. Select `APK` → `Next`
3. Create new keystore atau use existing:
   - **New Keystore**:
     - Key store path: Pilih lokasi & nama file
     - Password: Buat password yang kuat
     - Alias: Nama key (contoh: mygallery)
     - Validity: 25 years (standard)
     - Fill Certificate info
   - **Existing Keystore**:
     - Browse ke keystore file
     - Enter passwords
4. Select `release` build variant
5. Click `Finish`

#### Option B: Command Line

```bash
# Generate keystore (once only):
keytool -genkey -v -keystore my-gallery.keystore -alias mygallery -keyalg RSA -keysize 2048 -validity 10000

# Sign APK:
jarsigner -verbose -sigalg SHA256withRSA -digestalg SHA-256 -keystore my-gallery.keystore app/build/outputs/apk/release/app-release-unsigned.apk mygallery

# Zipalign:
zipalign -v 4 app/build/outputs/apk/release/app-release-unsigned.apk app-release.apk
```

### 7. Run on Device/Emulator

#### Setup Emulator (if needed):

1. `Tools` → `Device Manager`
2. `Create Device`
3. Select device (e.g., Pixel 5)
4. Select system image (API 34 recommended)
5. Click `Finish`

#### Run Application:

1. Select target device dari dropdown
2. Click Run (▶️) atau press `Shift + F10`
3. Wait for build & installation
4. App akan launch otomatis

## 🐛 Troubleshooting Build Issues

### Issue: Gradle Sync Failed

**Solution:**

```bash
# Clean and rebuild:
./gradlew clean
File → Invalidate Caches → Restart

# Check internet connection
# Check gradle.properties encoding (UTF-8)
```

### Issue: Compilation Error

**Common causes:**

- JDK version mismatch
- Kotlin plugin outdated
- Dependencies conflict

**Solution:**

```bash
# Update Kotlin plugin:
Check build.gradle.kts for latest versions

# Refresh dependencies:
./gradlew build --refresh-dependencies
```

### Issue: Out of Memory

**Solution:**
Edit `gradle.properties`:

```properties
org.gradle.jvmargs=-Xmx4096m -Dfile.encoding=UTF-8
```

### Issue: SDK Not Found

**Solution:**

- Verify `local.properties` exists dan path benar
- Install required SDK packages via SDK Manager

### Issue: Icon Missing

**Solution:**

- Follow icon setup steps di atas
- Ensure mipmap folders exist di `app/src/main/res/`
- Check AndroidManifest.xml references correct icon

## 📊 Build Optimization

### Faster Builds:

```properties
# Add to gradle.properties:
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.configureondemand=true
```

### Reduce APK Size:

```kotlin
// In app/build.gradle.kts:
android {
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(...)
        }
    }
}
```

## 🚀 Build for Different Platforms

### Build Debug APK:

```bash
./gradlew assembleDebug
```

- Size: Larger (~10-20MB)
- Debuggable: Yes
- Obfuscated: No

### Build Release APK:

```bash
./gradlew assembleRelease
```

- Size: Smaller (after ProGuard)
- Debuggable: No
- Obfuscated: Yes (if enabled)

### Build App Bundle (for Play Store):

```bash
./gradlew bundleRelease

# Output:
app/build/outputs/bundle/release/app-release.aab
```

## 📝 Pre-Release Checklist

Before releasing:

- [ ] Icon properly configured
- [ ] Version code incremented (`versionCode` in build.gradle.kts)
- [ ] Version name updated (`versionName` in build.gradle.kts)
- [ ] ProGuard rules tested
- [ ] APK signed with release keystore
- [ ] Tested on multiple devices
- [ ] All permissions working
- [ ] No debug logs in production
- [ ] Changelog updated

## 🎯 Build Success Indicators

✅ Gradle sync successful
✅ No compilation errors
✅ All dependencies resolved
✅ Icon visible in launcher
✅ App installs without errors
✅ Permissions request working
✅ Media loads correctly

## 📚 Additional Resources

- [Android Developer Guide](https://developer.android.com/studio/build)
- [Gradle Documentation](https://docs.gradle.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)

---

**Happy Building! 🎉**

_For issues not covered here, check GitHub Issues or review the codebase documentation._
