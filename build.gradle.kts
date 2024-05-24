plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "com.github.kaiquy"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")

    implementation("com.github.SaiintBrisson.command-framework:bukkit:1.3.1")
    implementation("org.jetbrains:annotations:24.0.0")
    implementation("de.tr7zw:item-nbt-api:2.11.2")
    implementation ("fr.mrmicky:fastboard:2.1.2")
    implementation(fileTree("inventory"))

    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
}

bukkit {
    main = "com.github.kaiquy.kitpvp.KitPvPPlugin"
    version = "${project.version}"
    author = "Kaique"
}

tasks {
    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

        shadowJar {
            archiveFileName.set("${project.name}.jar")

            relocate(
                "me.saiintbrisson",
                "com.github.kaiquy.misc.command"
            )

            relocate(
                "de.tr7zw",
                "com.github.kaiquy.misc.nbt"
            )

            relocate (
                "fr.mrmicky.fastboard",
                "com.github.kaiquy.misc.fastboard"
            )

            relocate(
                "me.devnatan.inventoryframework",
                "com.github.kaiquy.misc.inventory"
            )

            destinationDirectory.set(file("C:/Users/kaiqu/Desktop/Server/plugins"))
        }
    }
    compileJava {
        options.encoding = "UTF-8"
    }
}