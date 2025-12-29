# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep data classes
-keep class com.imphnen.mygallery.data.** { *; }

# Keep ViewModels
-keep class * extends androidx.lifecycle.ViewModel { *; }
