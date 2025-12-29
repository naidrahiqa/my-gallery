# My Gallery - Final Build Script
# This script will build the APK without Android Studio

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  MY GALLERY - APK BUILD SCRIPT" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Find Gradle 8.2
Write-Host "[1/5] Locating Gradle 8.2..." -ForegroundColor Yellow
$gradlePath = Get-ChildItem "$env:USERPROFILE\.gradle\wrapper\dists\gradle-8.2-bin" -Recurse -Filter "gradle.bat" -ErrorAction SilentlyContinue | Select-Object -First 1

if (-not $gradlePath) {
    Write-Host "✗ Gradle 8.2 not found!" -ForegroundColor Red
    Write-Host "  Trying to use gradlew.bat instead..." -ForegroundColor Yellow
    
    if (Test-Path ".\gradlew.bat") {
        Write-Host "✓ Using gradlew.bat" -ForegroundColor Green
        $useGradlew = $true
    } else {
        Write-Host "✗ gradlew.bat not found either!" -ForegroundColor Red
        exit 1
    }
} else {
    Write-Host "✓ Found Gradle at: $($gradlePath.FullName)" -ForegroundColor Green
    $useGradlew = $false
}

Write-Host ""

# Clean build folder
Write-Host "[2/5] Cleaning previous build..." -ForegroundColor Yellow
if (Test-Path "app\build") {
    Remove-Item "app\build" -Recurse -Force -ErrorAction SilentlyContinue
    Write-Host "✓ Cleaned build folder" -ForegroundColor Green
} else {
    Write-Host "✓ No previous build to clean" -ForegroundColor Green
}

Write-Host ""

# Run Gradle build
Write-Host "[3/5] Building APK (this may take a few minutes)..." -ForegroundColor Yellow
Write-Host "      Please wait..." -ForegroundColor Gray
Write-Host ""

try {
    if ($useGradlew) {
        # Use gradlew.bat
        $process = Start-Process -FilePath "cmd.exe" -ArgumentList "/c", "gradlew.bat", "assembleDebug" -Wait -NoNewWindow -PassThru -RedirectStandardOutput "build_output.txt" -RedirectStandardError "build_error.txt"
    } else {
        # Use system Gradle
        $gradleCmd = $gradlePath.FullName
        $process = Start-Process -FilePath "cmd.exe" -ArgumentList "/c", "`"$gradleCmd`"", "assembleDebug" -Wait -NoNewWindow -PassThru -RedirectStandardOutput "build_output.txt" -RedirectStandardError "build_error.txt"
    }
    
    $exitCode = $process.ExitCode
    
    Write-Host ""
    Write-Host "[4/5] Checking build result..." -ForegroundColor Yellow
    
    if ($exitCode -eq 0 -and (Test-Path "app\build\outputs\apk\debug\app-debug.apk")) {
        Write-Host "✓ Build completed successfully!" -ForegroundColor Green
        
        Write-Host ""
        Write-Host "[5/5] APK Details:" -ForegroundColor Yellow
        
        $apk = Get-Item "app\build\outputs\apk\debug\app-debug.apk"
        $apkSizeMB = [math]::Round($apk.Length / 1MB, 2)
        
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Green
        Write-Host "  BUILD SUCCESSFUL! ✓" -ForegroundColor Green
        Write-Host "========================================" -ForegroundColor Green
        Write-Host ""
        Write-Host "APK Location:" -ForegroundColor Cyan
        Write-Host "  $($apk.FullName)" -ForegroundColor White
        Write-Host ""
        Write-Host "APK Size: $apkSizeMB MB" -ForegroundColor Cyan
        Write-Host "App Name: My Gallery" -ForegroundColor Cyan
        Write-Host "Version: 1.0" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "Next Steps:" -ForegroundColor Yellow
        Write-Host "  1. Transfer APK to your Android device" -ForegroundColor White
        Write-Host "  2. Install the APK" -ForegroundColor White
        Write-Host "  3. Grant storage permissions when prompted" -ForegroundColor White
        Write-Host "  4. Enjoy your gallery app!" -ForegroundColor White
        Write-Host ""
        
        # Open folder
        Write-Host "Opening APK folder..." -ForegroundColor Gray
        explorer.exe "app\build\outputs\apk\debug\"
        
    } else {
        Write-Host "✗ Build failed!" -ForegroundColor Red
        Write-Host ""
        Write-Host "Error details:" -ForegroundColor Yellow
        
        if (Test-Path "build_error.txt") {
            $errors = Get-Content "build_error.txt" -Raw
            if ($errors) {
                Write-Host $errors -ForegroundColor Red
            }
        }
        
        if (Test-Path "build_output.txt") {
            $output = Get-Content "build_output.txt" | Select-Object -Last 20
            Write-Host "`nLast 20 lines of output:" -ForegroundColor Yellow
            $output | ForEach-Object { Write-Host $_ -ForegroundColor Gray }
        }
        
        Write-Host ""
        Write-Host "Possible solutions:" -ForegroundColor Yellow
        Write-Host "  1. Check internet connection (for downloading dependencies)" -ForegroundColor White
        Write-Host "  2. Try running this script again" -ForegroundColor White
        Write-Host "  3. Check build_output.txt for detailed errors" -ForegroundColor White
    }
    
} catch {
    Write-Host "✗ Error occurred during build!" -ForegroundColor Red
    Write-Host $_.Exception.Message -ForegroundColor Red
}

Write-Host ""
Write-Host "Press any key to exit..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
