// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

// Cada Sync/build reconsulta SNAPSHOTs en GitHub Packages (p. ej. buscaminas:1.0.0-SNAPSHOT)
subprojects {
    configurations.configureEach {
        resolutionStrategy {
            cacheChangingModulesFor(0, java.util.concurrent.TimeUnit.SECONDS)
            cacheDynamicVersionsFor(0, java.util.concurrent.TimeUnit.SECONDS)
        }
    }
}
