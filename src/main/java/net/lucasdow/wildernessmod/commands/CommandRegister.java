package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

interface Command {
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated);
}

public class CommandRegister {

    public static void init() {
        PVPLoot.init();
        RandomLoot.init();

        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {
            new RandomLoot().register(dispatcher, dedicated);
            new PVPLoot().register(dispatcher, dedicated);
            new PlayerCount().register(dispatcher, dedicated);
            new Loot().register(dispatcher, dedicated);
            new StoryTelling().register(dispatcher, dedicated);
            new SpawnChestCommand().register(dispatcher, dedicated);
        }));
    }
}
