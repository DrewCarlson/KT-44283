plugins {
    kotlin("multiplatform") version "1.4.21"
}

group = "test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    macosX64("macos") {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
        compilations.getByName("main") {
            val nativecode by cinterops.creating {
                compilerOpts("-lpthread")
            }
        }
    }
}
