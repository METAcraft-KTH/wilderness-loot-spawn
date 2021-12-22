package net.lucasdow.wildernessmod.commands;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class CommandRegister {

    public static void init() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {
            new Wilderness().register(dispatcher, dedicated);
        }));
    }
}
