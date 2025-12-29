import urllib.request
import os
import zipfile

print("Downloading Gradle wrapper jar...")

# URL untuk gradle wrapper jar versi 8.2
wrapper_jar_url = "https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradle/wrapper/gradle-wrapper.jar"
wrapper_jar_path = "gradle/wrapper/gradle-wrapper.jar"

# Buat folder jika belum ada
os.makedirs("gradle/wrapper", exist_ok=True)

# Download gradle-wrapper.jar
try:
    urllib.request.urlretrieve(wrapper_jar_url, wrapper_jar_path)
    print(f"✓ Downloaded gradle-wrapper.jar to {wrapper_jar_path}")
    print(f"✓ File size: {os.path.getsize(wrapper_jar_path)} bytes")
    print("\n✅ Gradle wrapper setup complete!")
except Exception as e:
    print(f"❌ Error downloading: {e}")
    print("\nAlternative: Gradle wrapper will be downloaded on first build")
