package net.Slainlight.NoHunger;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.loader.api.FabricLoader;

public class NoHungerConfigHandler {
    public static NoHungerConfig config = getConfig();
    public static boolean configAvail = false;

    public static void init() {

    }

    public static NoHungerConfig getConfig()
    {
        if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
            configAvail = true;
            AutoConfig.register(NoHungerConfig.class, JanksonConfigSerializer::new);
            return AutoConfig.getConfigHolder(NoHungerConfig.class).getConfig();
        } else {
            return null;
        }
    }

    public static boolean shouldInstantEat()
    {
        if (configAvail) {
            return config.shouldInstantEat();
        } else {
            return false;
        }
    }
    public static boolean shouldMakeFoodUnstackable()
    {
        if (configAvail) {
            return config.shouldMakeFoodUnstackable();
        } else {
            return false;
        }
    }
    public static boolean shouldShowExp()
    {
        if (configAvail) {
            return config.shouldShowExp();
        } else {
            return false;
        }
    }
    public static boolean shouldHideExp()
    {
        if (configAvail) {
            return config.shouldHideExp();
        } else {
            return false;
        }
    }
}
