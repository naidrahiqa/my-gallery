import urllib.request

print("Downloading proper gradlew.bat from Gradle repository...")

# URL untuk gradlew.bat dari Gradle 8.2
gradlew_bat_url = "https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradlew.bat"
gradlew_bat_path = "gradlew.bat"

try:
    urllib.request.urlretrieve(gradlew_bat_url, gradlew_bat_path)
    print(f"✓ Downloaded gradlew.bat")
    print(f"✓ File size: {os.path.getsize(gradlew_bat_path)} bytes")
    
    # Also download gradlew for Unix systems (optional)
    gradlew_url = "https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradlew"
    gradlew_path = "gradlew"
    urllib.request.urlretrieve(gradlew_url, gradlew_path)
    print(f"✓ Downloaded gradlew (Unix)")
    
    print("\n✅ Gradle wrapper scripts downloaded successfully!")
except Exception as e:
    print(f"❌ Error: {e}")

import os
