apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUI))
    "implementation"(project(Modules.moviesFavoritesDomain))

    "implementation"(Coil.coilCompose)
    "implementation"(Glide.glide)
    "implementation"(Glide.kaptGlide)
    
    "implementation"(project(Modules.repositoryRemote))
    "implementation"(project(Modules.repositoryRetrofit))
}