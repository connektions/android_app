import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.0" apply false
    id("com.android.library") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    alias(libs.plugins.detekt)
}

allprojects {

    apply(plugin = "io.gitlab.arturbosch.detekt")

    dependencies {
        /**using rootProject.project instead of
         * simple libs because gradle don't fix problem with access
         * see: https://github.com/gradle/gradle/issues/16634#issuecomment-809345790
         **/
        detektPlugins(rootProject.project.libs.util.detektPlugin)
        detektPlugins(rootProject.project.libs.util.detektFormatting)
    }
    tasks.withType<Detekt>().configureEach {

        jvmTarget = JavaVersion.VERSION_18.toString()
        setSource(files("src"))
        exclude("**/androidTest/**")
        exclude("**/assets/**")
        exclude("**/res/**")
        exclude("**/test/**")
        reports {
            this.html.enabled = true
            html.destination = file("${buildDir}/reports/detekt-results.html")
        }
    }
    tasks.withType<DetektCreateBaselineTask>().configureEach {
        jvmTarget = JavaVersion.VERSION_18.toString()
    }

    configure<DetektExtension> {
        toolVersion = rootProject.project.libs.versions.detekt.get()
        buildUponDefaultConfig = true
        autoCorrect = true
    }


}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

