import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
    java
    `java-library`

    `maven-publish`

    id("io.papermc.paperweight.userdev") version "1.7.3"
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.2.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("io.github.goooler.shadow") version "8.1.8"
    kotlin("jvm")
}

description = "A plugin that changes the way kill credit is given."

group = "enderman.dev"
version = "0.0.1"

val projectGroupString = group.toString()
val projectVersionString = version.toString()

val javaVersion = 21
val javaVersionEnumMember = JavaVersion.valueOf("VERSION_$javaVersion")

val paperApiMinecraftVersion = "1.21.1"
val paperApiVersion = "$paperApiMinecraftVersion-R0.1-SNAPSHOT"

java {
    sourceCompatibility = javaVersionEnumMember
    targetCompatibility = javaVersionEnumMember

    toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion))
}

repositories {
    mavenCentral()
}

dependencies {
    paperweight.paperDevBundle(paperApiVersion)

    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    compileJava {
        options.release.set(javaVersion)
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
}

bukkitPluginYaml {
    name = "BetterCombat"
    description = project.description

    authors = listOfNotNull("Esoteric Enderman")

    setVersion(project.version)

    apiVersion = paperApiMinecraftVersion
    main = "${project.group}.minecraft.plugins.combat.better.BetterCombatPlugin"

    load = BukkitPluginYaml.PluginLoadOrder.STARTUP
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = projectGroupString
            artifactId = rootProject.name
            version = projectVersionString
        }
    }
}

tasks.named("publishMavenJavaPublicationToMavenLocal") {
    dependsOn(tasks.named("build"))
}
