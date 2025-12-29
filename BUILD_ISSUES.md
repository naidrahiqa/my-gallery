# Build Issues - My Gallery Android App

## ✅ SOLVED: Local Build Success Strategy

Kami berhasil memperbaiki environment build lokal dengan langkah-langkah berikut:

### 1. Masalah Java Version (Java 25 vs Java 8 vs Java 17)

- **Solusi**: Menggunakan **JDK 17 Portable** yang didownload langsung ke folder project (`jdk-17.0.2`).
- Ini menghindari konflik dengan instalasi Java sistem (Java 25/8).

### 2. Masalah Gradle Version

- **Problem**: Android Gradle Plugin 8.3.0 butuh Gradle 8.4+.
- **Solusi**: Update `gradle-wrapper.properties` ke Gradle **8.4**.

### 3. Masalah Resource (Icon)

- **Problem**: `ic_launcher` merujuk ke `@color/ic_launcher_background` yang sudah dihapus.
- **Solusi**: Update XML referensi ke `@drawable/ic_launcher_background`.

### 4. Masalah Android SDK Path

- **Solusi**: Set environment variable `ANDROID_HOME` secara manual ke `C:\Users\CASTORICE\AppData\Local\Android\Sdk`.

## 🚀 Cara Build Manual (Jika perlu restart)

Gunakan perintah PowerShell berikut langkah demi langkah:

```powershell
# 1. Set Java Home ke JDK 17 Portable (pastikan folder ada)
$env:JAVA_HOME = "$PWD\jdk-17.0.2"

# 2. Set Android SDK Path
$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"

# 3. Jalankan Build
.\gradlew.bat clean assembleDebug
```

## 📦 Lokasi Output APK

Setelah build sukses, APK ada di:
`app\build\outputs\apk\debug\app-debug.apk`
