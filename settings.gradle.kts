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

rootProject.name = "Laborator06"

include(":javaSkelSingleThreadedServer")
include(":javaSkelClientServerCommunication")
include(":kotlinSkelSingleThreadedServer")
include(":kotlinSkelClientServerCommunication")
include(":javaSolutionSingleThreadedServer")
include(":javaSolutionClientServerCommunication")
include(":kotlinSolutionSingleThreadedServer")
include(":kotlinSolutionClientServerCommunication")
include(":tcpclientlab06")
