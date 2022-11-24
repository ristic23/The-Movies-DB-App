apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUI))

    "implementation"(project(Modules.repositoryRemote))
    "implementation"(project(Modules.repositoryRetrofit))

    "implementation"(project(Modules.moviesListDomain))

    "implementation"(Glide.glide)
    "implementation"(Glide.kaptGlide)
}