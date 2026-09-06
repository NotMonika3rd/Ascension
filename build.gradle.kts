plugins {
    id("java")
    id("xyz.wagyourtail.unimined") version "1.4.36-kappa"
    id("idea")
}

group = "rip.sayori"
version = "1.0-SNAPSHOT"

unimined.minecraft {
    version = "1.12.2"

    mappings.mcp("stable", "39-1.12")

    cleanroom {
        loader("0.6.12-alpha")
        runs.all {
            systemProperty("crl.dev.mixin", "ascension.mixin.json")
        }
    }
}
repositories {
    mavenCentral()
    unimined.curseMaven()
}

dependencies {
    "modImplementation"("curse.maven:BaublesEX-1096600:8769637")
}

idea {
    module {
        isDownloadJavadoc = false
        isDownloadSources = false
    }
}

tasks.jar {
    doFirst {
        manifest.attributes(
            "ModType" to "CRL",
            "MixinConfigs" to "ascension.mixin.json"
        )
    }
}