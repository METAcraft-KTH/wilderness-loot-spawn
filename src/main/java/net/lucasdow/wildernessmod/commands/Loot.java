package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import java.util.UUID;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class Loot implements Command {

    public void displayRandomLoot(CommandContext<ServerCommandSource> context, int tier) throws CommandSyntaxException {
        LiteralText message = new LiteralText("");

        switch (tier) {
            case 1 -> message = new LiteralText("- 4 TNT\n- 1 Diamond\n");
            case 2 -> message = new LiteralText("- 8 TNT\n- 2 Diamonds\n- 1 Golden Apple\n");
            case 3 -> message = new LiteralText("- 12 TNT\n- 2 Diamonds\n- 1 Turtle Helmet\n");
            case 4 -> message = new LiteralText("- 32 TNT\n- 4 Diamonds\n- 8 Golden Apples\n- 32 Obsidian\n");
            case 5 -> message = new LiteralText("- 32 TNT\n- 8 Diamonds\n- 32 Golden Apples\n- 2 Shulker Shells\n- 1 Netherite Ingot\n");
            case 6 -> message = new LiteralText("- 32 Diamonds\n- 2 Beacons\n- 2 Shulker Shells\n- 2 Netherite Ingots\n- 2 End Crystals\n- 1 Enchanted Golden Apple\n");
            case 7 -> message = new LiteralText("- 64 Diamonds\n- 4 Beacons\n- 2 Enchanted Golden Apples\n- 1 Pair of Premium Wings (Elytra)\n");
            default -> {
            }
        }

        LiteralText playerRange = PlayerCount.getTierPlayerRange(tier);
        LiteralText introMessage = new LiteralText("Tier " + tier + " Random Loot Crate consists of:\n");
        introMessage.append(message.formatted(Formatting.AQUA)
                .append(new LiteralText("Spawns when ").formatted(Formatting.WHITE)
                .append(playerRange)
                .append(new LiteralText(" are online!").formatted(Formatting.WHITE))));
        context.getSource().getPlayer().sendMessage(introMessage, false);
    }

    public void displayPVPLoot(CommandContext<ServerCommandSource> context, int tier) throws CommandSyntaxException {
        LiteralText message = new LiteralText("");

        switch (tier) {
            case 1 -> message = new LiteralText("- 4 TNT\n- 2 Diamonds\n- Tier 1 Battle Token\n");
            case 2 -> message = new LiteralText("- 8 TNT\n- 4 Diamonds\n-4 Golden Apples\n- Tier 2 Battle Token\n");
            case 3 -> message = new LiteralText("- 8 Diamonds\n-8 Golden Apples\n- Tier 3 Battle Token\n");
            case 4 -> message = new LiteralText("- 32 TNT\n- 10 Diamonds\n- 16 Golden Apples\n- 1 Diamond Block\n- 1 Shulker Shell\n- Tier 4 Battle Token\n");
            case 5 -> message = new LiteralText("- 64 TNT\n- 32 Diamonds\n- 16 Golden Apples\n- 2 Shulker Shells\n- 2 Netherite Ingots\n- 4 End Crystals\n- Tier 5 Battle Token\n");
            case 6 -> message = new LiteralText("- 32 Diamonds\n- 2 Beacons\n- 2 Shulker Boxes\n- 4 Netherite Ingots\n- 1 Enchanted Golden Apple\n- Tier 6 Battle Token\n");
            case 7 -> message = new LiteralText("- 4 Diamond Blocks\n- 6 Beacons\n- 2 Enchanted Golden Apples\n- 2 Netherite Blocks\n- 1 Titanium Chestplate (Netherite Chest plate with Protection V)" +
                    "\n- 1 Atom Sword (Netherite sword with Sharpness VI)\n- Tier 7 Battle Token\n");
            default -> {
            }
        }

        LiteralText playerRange = PlayerCount.getTierPlayerRange(tier);
        LiteralText introMessage = new LiteralText("Tier " + tier + " PVP Loot Crate consists of:\n");
        introMessage.append(message.formatted(Formatting.LIGHT_PURPLE)
                .append(new LiteralText("Spawns when ").formatted(Formatting.WHITE)
                .append(playerRange)
                .append(new LiteralText(" are online!").formatted(Formatting.WHITE))));
        context.getSource().getPlayer().sendMessage(introMessage, false);
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated) {
        dispatcher.register(literal("random-loot")
            .then(argument("tier", IntegerArgumentType.integer())
                .executes(context -> {
                    int tier = IntegerArgumentType.getInteger(context, "tier");
                    PlayerEntity player = context.getSource().getPlayer();

                    if (tier <= 0 || tier > 7) {
                        player.sendMessage(new LiteralText("Tier must be between 1 to 7 inclusive!").formatted(Formatting.RED), false);
                        return 1;
                    }

                    displayRandomLoot(context, tier);

                    return 0;
                })
            )
        );

        dispatcher.register(literal("pvp-loot")
            .then(argument("tier", IntegerArgumentType.integer())
                .executes(context -> {
                    int tier = IntegerArgumentType.getInteger(context, "tier");
                    PlayerEntity player = context.getSource().getPlayer();

                    if (tier <= 0 || tier > 7) {
                        player.sendMessage(new LiteralText("Tier must be between 1 to 7 inclusive!").formatted(Formatting.RED), false);
                        return 1;
                    }

                    displayPVPLoot(context, tier);

                    return 0;
                })
            )
        );
    }
}
