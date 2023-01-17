package net.Slainlight.NoHunger;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name="nohunger")
public class NoHungerConfig implements ConfigData
{
    boolean InstantEat = true;
    boolean makeFoodUnstackable = true;
    @Comment("(disables sneak to show in favor of always showing)")
    boolean showExp = false;
    @Comment("(disables sneak to show in favor of never showing)")
    boolean hideExp = false;

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
    public boolean shouldHideExp()
    {
        return hideExp;
    }
}