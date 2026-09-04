package org.xviizyk.fabric;

import net.fabricmc.api.ModInitializer;

import org.xviizyk.Screenify;

public final class ScreenifyFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Screenify.init();
    }
}
