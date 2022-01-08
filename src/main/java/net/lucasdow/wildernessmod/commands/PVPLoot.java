package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.lucasdow.LootCrates.PVPLootCrateBase;
import net.lucasdow.LootCrates.RandomLootBase;
import net.lucasdow.LootCrates.tiers.pvp.*;
import net.lucasdow.wildernessmod.WildernessMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.Random;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class PVPLoot implements Command {

    public static HashMap<Integer, PVPLootCrateBase> tierClasses = new HashMap<Integer, PVPLootCrateBase>();

    public static void init() {
        tierClasses.put(1, new PvPTier1());
        tierClasses.put(2, new PvPTier2());
        tierClasses.put(3, new PvPTier3());
        tierClasses.put(4, new PvPTier4());
        tierClasses.put(5, new PvPTier5());
        tierClasses.put(6, new PvPTier6());
        tierClasses.put(7, new PvPTier7());
    }

    public void spawnPVPLootChest(int tier, CommandContext<ServerCommandSource> context) {
        PVPLootCrateBase randomLootTierClass = tierClasses.get(tier);
        randomLootTierClass.setTier(tier);
        randomLootTierClass.setSource(context.getSource(), context.getSource().getWorld().getRandom());
        randomLootTierClass.createChest();
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated) {
        dispatcher.register(literal("wilderness")
            .then(literal("spawnPVPLoot")
                .requires(source -> source.hasPermissionLevel(4))
                .executes(context -> {
                    WildernessMod.LOGGER.info("Spawning PVP-loot!");

                    int playerCount = PlayerCount.getPlayerCount(context);
                    int tier = PlayerCount.getTier(playerCount);

                    if (tier == 0) { return 0; }

                    spawnPVPLootChest(tier, context);

                    return 0;
                })
            )
        );

        dispatcher.register(literal("wilderness")
            .then(literal("forcePVPLootSpawn")
                .then(argument("tier", IntegerArgumentType.integer())
                    .requires(source -> source.hasPermissionLevel(4))
                    .executes(context -> {
                        int tier = IntegerArgumentType.getInteger(context, "tier");
                        PlayerEntity player = context.getSource().getPlayer();

                        if (tier <= 0 || tier > 7) {
                            player.sendMessage(new LiteralText("Tier must be between 1 to 7 inclusive!").formatted(Formatting.RED), false);
                            return 1;
                        }

                        spawnPVPLootChest(tier, context);

                        return 0;
                    })
                )
            )
        );
    }
}
