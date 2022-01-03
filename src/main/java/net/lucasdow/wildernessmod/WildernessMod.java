package net.lucasdow.wildernessmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.lucasdow.wildernessmod.commands.CommandRegister;
import net.lucasdow.wildernessmod.commands.RandomLoot;
import net.minecraft.loot.LootTables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WildernessMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "wildernessmod";
	public static final Logger LOGGER = LogManager.getLogger("wildernessmod");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		CommandRegister.init();

		LOGGER.info("Wilderness Mod by Lucas Dow Loaded!");
	}
}
