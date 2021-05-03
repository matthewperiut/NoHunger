package net.Slainlight.NoHunger;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

@Config(name="NoHunger")
public class NoHungerConfig implements ConfigData
{

    boolean InstantEat = false;
    boolean makeFoodUnstackable = false;

    static boolean Registered = false;
    public static void Register()
    {
        Registered = true;
    }

    public static boolean isRegistered()
    {
        return Registered;
    }

    public boolean shouldInstantEat()
    {
        return InstantEat;
    }
    public boolean shouldMakeFoodUnstackable()
    {
        return makeFoodUnstackable;
    }
}