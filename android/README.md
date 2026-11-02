# Hurrican Android native build

This project adds the first native Android build scaffolding for Hurrican in a controller-first, touch-light configuration. The goal is to run the game with SDL gamepad/joystick controls only and to keep touch input out of the initial version.

## What is included

- Android app skeleton for a fullscreen SDL activity
- Android CMake configuration for the native library build
- SDL joystick/gamepad integration prepared through the existing `SDL_Joystick` and `SDL_GameController` code paths already used by the project
- A first-pass Android manifest tuned for landscape game play

## Important notes

- The repository already contains joystick support via SDL (`DX8Joystick.cpp`, `DX8Input.cpp`, `gamecontrollerdb.txt`).
- The Android project expects an SDL2 package or Android SDL source tree to be available during the build.
- Touch input is intentionally suppressed in the Android native configuration for the first version.

## Build

```bash
cd android
./gradlew assembleDebug
```

If the SDL2 package is not available, add it to the Android project or point `SDL2_DIR` to the installed SDL CMake package before building.
