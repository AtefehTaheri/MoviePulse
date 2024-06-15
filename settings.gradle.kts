pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MoviePulse"
include(":app")
include(":data:upcoming-list-remote")
include(":core:network")
include(":core:common")
include(":data:upcoming-list-repository")
include(":feature:upcominglist")
include(":core:database")
include(":navigation")
include(":feature:homescreen")
include(":feature:nowplaying")
include(":data:now-playing-remote")
include(":data:now-playing-repository")
