// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

subprojects {
    // You can apply common configurations for all sub-projects here
}

buildscript {
    // Optional: You can also define repositories or dependencies common to all modules here
}
