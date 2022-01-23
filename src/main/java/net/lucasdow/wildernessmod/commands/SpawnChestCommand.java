package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.lucasdow.wildernessmod.WildernessMod;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import java.util.Random;
import java.util.UUID;

import static net.lucasdow.wildernessmod.commands.PlayerCount.addTierToMessage;
import static net.lucasdow.wildernessmod.commands.PlayerCount.getTier;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class SpawnChestCommand implements Command {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated) {
        dispatcher.register(literal("wilderness")
            .then(literal("prepare")
                .then(argument("loot_type", StringArgumentType.greedyString())
                    .requires(source -> source.hasPermissionLevel(4))
                    .executes(context -> {
                        String lootType = StringArgumentType.getString(context, "loot_type");
                        int playerCount = context.getSource().getServer().getCurrentPlayerCount();
                        PlayerCount.setPlayerCount(context.getSource().getServer().getDataCommandStorage(), playerCount);

                        switch (lootType) {
                            case "pvp":
                                LiteralText message = (LiteralText) new LiteralText("[PVP Event] ").formatted(Formatting.BOLD)
                                        .append(new LiteralText("Current number players: " + playerCount + ".\n").formatted(Formatting.LIGHT_PURPLE));

                                int tier = PlayerCount.getTier(playerCount);

                                if (tier == 0) { return 0; }
                                else { PlayerCount.addTierToMessage(tier, message); }

                                message.append(new LiteralText("Loot will spawn in 15 minutes, get ready!\n").formatted(Formatting.LIGHT_PURPLE))
                                        .append(new LiteralText("To get more info about the loot, type ").formatted(Formatting.YELLOW).formatted(Formatting.ITALIC))
                                        .append(new LiteralText("/pvp-loot <tier>").formatted(Formatting.GOLD));

                                context.getSource().getServer().getPlayerManager().broadcast(message, MessageType.CHAT, UUID.randomUUID());

                                break;
                            case "random":
                                break;
                            default:
                                break;
                        }

                        return 0;
                    })
                )
            )
        );
    }
}
