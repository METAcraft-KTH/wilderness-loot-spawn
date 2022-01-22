package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.lucasdow.LootCrates.RandomLootBase;
import net.lucasdow.LootCrates.tiers.random.*;
import net.lucasdow.wildernessmod.WildernessMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.Random;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class RandomLoot implements Command {
    public static HashMap<Integer, RandomLootBase> tierClasses = new HashMap<Integer, RandomLootBase>();

    public static void init() {
        tierClasses.put(7, new RandomTier7());
        tierClasses.put(6, new RandomTier6());
        tierClasses.put(5, new RandomTier5());
        tierClasses.put(4, new RandomTier4());
        tierClasses.put(3, new RandomTier3());
        tierClasses.put(2, new RandomTier2());
        tierClasses.put(1, new RandomTier1());
    }

    public void spawnRandomLootChest(int tier, CommandContext<ServerCommandSource> context) {
        RandomLootBase randomLootTierClass = tierClasses.get(tier);
        randomLootTierClass.setTier(tier);
        randomLootTierClass.setSource(context.getSource(), context.getSource().getWorld().getRandom());
        randomLootTierClass.createChest();
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated) {
        dispatcher.register(literal("wilderness")
            .then(literal("spawnRandomLoot")
                .requires(source -> source.hasPermissionLevel(4))
                .executes(context -> {
                    WildernessMod.LOGGER.info("Placing random loot!");

                    Random random = context.getSource().getWorld().getRandom();

                    int playerCount = PlayerCount.getPlayerCount(context);
                    int tier = PlayerCount.getTier(playerCount);

                    if (tier == 0) { return 0; }

                    spawnRandomLootChest(tier, context);

                    return 0;
                })
            )
        );

        dispatcher.register(literal("wilderness")
            .then(literal("forceRandomLootSpawn")
                .then(argument("tier", IntegerArgumentType.integer())
                    .requires(source -> source.hasPermissionLevel(4))
                    .executes(context -> {
                        int tier = IntegerArgumentType.getInteger(context, "tier");
                        PlayerEntity player = context.getSource().getPlayer();

                        if (tier <= 0 || tier > 7) {
                            player.sendMessage(new LiteralText("Tier must be between 1 to 7 inclusive!").formatted(Formatting.RED), false);
                            return 1;
                        }

                        spawnRandomLootChest(tier, context);

                        return 0;
                    })
                )
            )
        );
    }
}