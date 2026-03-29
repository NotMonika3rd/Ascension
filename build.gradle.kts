plugins {
    id("java")
    id("xyz.wagyourtail.unimined") version "1.4.16-kappa"
}

group = "rip.sayori"
version = "1.0-SNAPSHOT"

unimined.minecraft {
    version = "1.12.2"

    mappings.mcp("stable", "39-1.12")

    cleanroom {
        loader("0.5.6-alpha")
    }
}
repositories {
    mavenCentral()
    unimined.curseMaven()
}

dependencies {
    "modImplementation"("curse.maven:BaublesEX-1096600:7803926")
}

tasks.test {
    useJUnitPlatform()
}