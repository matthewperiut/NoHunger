package net.Slainlight.NoHunger;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class Main implements ModInitializer
{
	public static int raised = 0;
	@Override
	public void onInitialize()
	{
		NoHungerConfigHandler.init();
		if (FabricLoader.getInstance().isModLoaded("raised"))
		{
			raised = 2;
		}
	}
}
