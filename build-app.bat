@echo off
setlocal

set GRADLE_HOME=C:\Users\CASTORICE\.gradle\wrapper\dists\gradle-8.14-all\dkiqj\gradle-8.14
set PATH=%GRADLE_HOME%\bin;%PATH%

echo Building My Gallery App...
echo.

gradle assembleDebug --stacktrace

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo BUILD SUCCESSFUL!
    echo ========================================
    echo.
    echo APK Location: app\build\outputs\apk\debug\app-debug.apk
    echo.
) else (
    echo.
    echo ========================================
    echo BUILD FAILED
    echo ========================================
    echo.
)

endlocal
pause
