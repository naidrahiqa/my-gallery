@echo off
echo ========================================
echo   MY GALLERY - APK BUILD
echo ========================================
echo.

echo [Step 1/3] Finding Gradle...
set GRADLE_DIR=C:\Users\CASTORICE\.gradle\wrapper\dists\gradle-8.2-bin

for /r "%GRADLE_DIR%" %%f in (gradle.bat) do (
    set GRADLE_CMD=%%f
    goto :found
)

:found
if not defined GRADLE_CMD (
    echo ERROR: Gradle not found!
    echo Trying gradlew.bat instead...
    set GRADLE_CMD=gradlew.bat
)

echo Found: %GRADLE_CMD%
echo.

echo [Step 2/3] Cleaning build...
if exist "app\build" rmdir /s /q "app\build"
echo Cleaned.
echo.

echo [Step 3/3] Building APK...
echo This will take 2-5 minutes, please wait...
echo.

"%GRADLE_CMD%" assembleDebug

if %ERRORLEVEL% EQU 0 (
    if exist "app\build\outputs\apk\debug\app-debug.apk" (
        echo.
        echo ========================================
        echo   BUILD SUCCESSFUL!
        echo ========================================
        echo.
        echo APK Location:
        echo app\build\outputs\apk\debug\app-debug.apk
        echo.
        echo Opening folder...
        start "" "app\build\outputs\apk\debug"
        echo.
        echo Next: Install APK to your Android device!
    ) else (
        echo.
        echo Build command completed but APK not found!
    )
) else (
    echo.
    echo ========================================
    echo   BUILD FAILED
    echo ========================================
    echo.
    echo Check errors above for details.
)

echo.
pause
