package com.matthewperiut.nohunger.fabric;

import com.matthewperiut.nohunger.NoHunger;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class NoHungerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config2"))
            NoHunger.isClothConfigPresent = true;
        NoHunger.init();
    }
}