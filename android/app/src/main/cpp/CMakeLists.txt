cmake_minimum_required(VERSION 3.22)

project(HurricanAndroid LANGUAGES C CXX)

set(HURRICAN_ROOT "${CMAKE_CURRENT_SOURCE_DIR}/../../../../Hurrican")

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_POSITION_INDEPENDENT_CODE ON)

if(ANDROID)
    add_definitions(-DANDROID=1 -D__ANDROID__)
    add_definitions(-DNO_TOUCH=1)
endif()

file(GLOB_RECURSE HURRICAN_SOURCES CONFIGURE_DEPENDS
    "${HURRICAN_ROOT}/src/*.cpp"
    "${HURRICAN_ROOT}/src/*.hpp"
)

add_library(hurrican SHARED ${HURRICAN_SOURCES})

target_include_directories(hurrican PRIVATE
    "${HURRICAN_ROOT}/src"
    "${HURRICAN_ROOT}/src/SDLPort"
    "${HURRICAN_ROOT}/3rdparty"
    "${HURRICAN_ROOT}/3rdparty/glm"
)

find_package(SDL2 CONFIG QUIET)
if(SDL2_FOUND)
    target_link_libraries(hurrican PRIVATE SDL2::SDL2)
else()
    message(WARNING
        "SDL2 not found for Android. Add the SDL2 package or copy the Android SDL project and set SDL2_DIR before building.\n"
        "Controller-first support is still enabled through SDL_GameController/SDL_Joystick on the native side."
    )
endif()

target_link_libraries(hurrican PRIVATE
    android
    log
    EGL
    GLESv2
)

if(ANDROID)
    target_compile_definitions(hurrican PRIVATE
        SDL_JAVA_PACKAGE_PATH=com/hurrican/android
    )
endif()
