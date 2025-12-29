# PowerShell Build Script for My Gallery
Write-Host "Building My Gallery App..." -ForegroundColor Cyan
Write-Host ""

# Set Gradle path
$gradleBat = "$env:USERPROFILE\.gradle\wrapper\dists\gradle-8.14-all\c2qonpi39x1mddn7hk5gh9iqj\gradle-8.14\bin\gradle.bat"

# Check if gradle exists
if (-Not (Test-Path $gradleBat)) {
    Write-Host "Error: Gradle not found at $gradleBat" -ForegroundColor Red
    exit 1
}

# Build the app
Write-Host "Running Gradle assembleDebug..." -ForegroundColor Yellow
& cmd /c "`"$gradleBat`" assembleDebug --stacktrace"

# Check build result
if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "========================================"  -ForegroundColor Green
    Write-Host "BUILD SUCCESSFUL!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    Write-Host ""
    Write-Host "APK Location: app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor Cyan
    
    # Check if APK exists
    if (Test-Path "app\build\outputs\apk\debug\app-debug.apk") {
        $apkSize = (Get-Item "app\build\outputs\apk\debug\app-debug.apk").Length / 1MB
        Write-Host ("APK Size: {0:N2} MB" -f $apkSize) -ForegroundColor Cyan
    }
} else {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Red
    Write-Host "BUILD FAILED" -ForegroundColor Red
    Write-Host "========================================" -ForegroundColor Red
    Write-Host ""
    Write-Host "Check the error messages above" -ForegroundColor Yellow
}
