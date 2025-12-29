# Icon Setup Instructions

## App Icon Location

The app icon (`icon.jpg`) is located in the root directory of this project.

## To Use This Icon in the App:

### Method 1: Using Android Asset Studio (Recommended)

1. Open Android Studio
2. Right-click on `app/src/main/res` folder
3. Select `New` → `Image Asset`
4. Choose `Launcher Icons (Adaptive and Legacy)`
5. In the "Foreground Layer" tab:
   - Select "Image" as Path type
   - Click the folder icon and browse to `icon.jpg`
6. Configure the icon:
   - Trim: Yes (if needed)
   - Resize: Adjust as needed (recommend 75-80%)
7. Click "Next" and then "Finish"
8. This will generate all required icon sizes and formats

### Method 2: Manual Conversion

1. Use an online tool like: https://romannurik.github.io/AndroidAssetStudio/
2. Upload `icon.jpg`
3. Download the generated icon pack
4. Extract and copy all folders to `app/src/main/res/`

### Generated Files

The icon should be generated for these densities:

- `mipmap-mdpi/` (48x48 px)
- `mipmap-hdpi/` (72x72 px)
- `mipmap-xhdpi/` (96x96 px)
- `mipmap-xxhdpi/` (144x144 px)
- `mipmap-xxxhdpi/` (192x192 px)

### Adaptive Icons (Android 8.0+)

For modern Android versions, also generate:

- `mipmap-anydpi-v26/ic_launcher.xml`
- Foreground and background drawables

## Current Icon Status

✅ Source icon file: `icon.jpg` (present in root directory)
⚠️ Mipmap resources: Need to be generated (see instructions above)

## Note

The current `AndroidManifest.xml` references `@mipmap/ic_launcher`. After generating the icon using one of the methods above, the app will display your custom icon.
