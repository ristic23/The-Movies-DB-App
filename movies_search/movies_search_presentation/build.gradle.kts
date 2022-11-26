apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUI))
    "implementation"(project(Modules.moviesSearchDomain))

    "implementation"(Coil.coilCompose)

    "implementation"(project(Modules.repositoryRemote))
    "implementation"(project(Modules.repositoryRetrofit))
}