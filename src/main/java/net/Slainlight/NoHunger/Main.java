package net.Slainlight.NoHunger;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {

	@Override
	public void onInitialize() {
		if(!NoHungerConfig.isRegistered())
		{
			AutoConfig.register(NoHungerConfig.class, JanksonConfigSerializer::new);
			NoHungerConfig.Register();
		}
		System.out.println("[NoHunger] Welcome to Alpha! haha");
	}
}
