package net.Slainlight.NoHunger;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class Main implements ModInitializer
{
	public static int raised = 0;
	public static NoHungerConfig config = getConfig();

	public static NoHungerConfig getConfig()
	{
		AutoConfig.register(NoHungerConfig.class, JanksonConfigSerializer::new);
		return AutoConfig.getConfigHolder(NoHungerConfig.class).getConfig();
	}
	@Override
	public void onInitialize()
	{
		if (FabricLoader.getInstance().isModLoaded("raised"))
		{
			raised = 2;
		}
	}
}
