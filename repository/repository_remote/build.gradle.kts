apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.repositoryRetrofit))
    "implementation"(project(Modules.repositoryRoomDb))

    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
    "implementation"(Paging.pagingRoom)
    "implementation"(Paging.paging)
}