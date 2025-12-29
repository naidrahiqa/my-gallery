# 🤖 Auto-Build dengan GitHub Actions

## Cara Menggunakan

### 1. Upload Project ke GitHub

```bash
# Initialize git (jika belum)
git init

# Add all files
git add .

# Commit
git commit -m "Initial commit - My Gallery v1.0"

# Create repository di GitHub dulu, lalu:
git remote add origin https://github.com/YOUR_USERNAME/my-gallery.git

# Push
git branch -M main
git push -u origin main
```

### 2. APK Akan Auto-Build!

Setelah push, GitHub Actions akan otomatis:

- ✅ Setup build environment
- ✅ Download dependencies
- ✅ Build debug APK
- ✅ Build release APK (unsigned)
- ✅ Upload APK sebagai artifacts

### 3. Download APK

1. Go to repository di GitHub
2. Click tab **Actions**
3. Click pada workflow run terbaru
4. Scroll ke bawah ke **Artifacts**
5. Download **app-debug.apk**
6. Install ke Android device!

## Trigger Manual Build

Bisa juga trigger build manual:

1. Go to **Actions** tab
2. Click **Android CI - Build APK**
3. Click **Run workflow**
4. Select branch → **Run workflow**

## Build Status

Setelah setup, Anda bisa tambahkan badge di README:

```markdown
![Android CI](https://github.com/YOUR_USERNAME/my-gallery/workflows/Android%20CI%20-%20Build%20APK/badge.svg)
```

## ✨ Keuntungan

- ✅ **Free**: Gratis tanpa batas
- ✅ **Otomatis**: Auto-build setiap push
- ✅ **No setup**: Tidak perlu install apapun
- ✅ **Artifacts**: APK tersimpan 90 hari
- ✅ **Professional**: CI/CD workflow

## 🎯 Build Time

Biasanya:

- First build: 5-10 menit
- Subsequent builds: 2-5 menit

## 📱 Install APK

Setelah download APK dari GitHub Actions:

1. Transfer ke Android device
2. Enable "Install from Unknown Sources"
3. Install APK
4. Grant storage permissions
5. Enjoy My Gallery!

---

**Sekarang Anda punya professional CI/CD pipeline!** 🚀
