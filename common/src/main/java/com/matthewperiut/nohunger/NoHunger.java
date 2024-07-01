package com.matthewperiut.nohunger;

import net.minecraft.util.Identifier;

public class NoHunger {
    public static final Identifier ARMOR_HALF_BACKWARDS_TEXTURE = Identifier.of("nohunger", "hud/armor_half");

    public static boolean isClothConfigPresent = false;

    public static final String MOD_ID = "nohunger";
    public static int raised = 0;
    public static void init()
    {
        NoHungerConfigHandler.init();
    }
}
