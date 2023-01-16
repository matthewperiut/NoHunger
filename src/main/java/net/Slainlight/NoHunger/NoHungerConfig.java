package net.Slainlight.NoHunger;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name="nohunger")
public class NoHungerConfig implements ConfigData
{
    boolean InstantEat = true;
    boolean makeFoodUnstackable = true;
    boolean showExp = false;

    public boolean shouldInstantEat()
    {
        return InstantEat;
    }
    public boolean shouldMakeFoodUnstackable()
    {
        return makeFoodUnstackable;
    }
    public boolean shouldShowExp()
    {
        return showExp;
    }
}