package com.hurrican.android;

public class HurricanActivity extends org.libsdl.app.SDLActivity {
    @Override
    protected String[] getLibraries() {
        return new String[] { "SDL2", "hurrican" };
    }

    @Override
    protected String[] getArguments() {
        return new String[] { "--native-android" };
    }
}
