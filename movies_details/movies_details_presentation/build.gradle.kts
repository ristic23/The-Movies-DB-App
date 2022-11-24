apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUI))
    "implementation"(project(Modules.moviesDetailsDomain))

    "implementation"(project(Modules.repositoryRemote))

    "implementation"(Coil.coilCompose)
}