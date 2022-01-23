package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.lucasdow.wildernessmod.WildernessMod;
import net.minecraft.command.DataCommandStorage;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.UUID;

import static net.minecraft.server.command.CommandManager.literal;

public class PlayerCount implements Command{

    static void setPlayerCount(DataCommandStorage storage, int playerCount) {
        Identifier id = new Identifier(WildernessMod.MOD_ID, "player_count");

        NbtCompound playerCountValue = storage.get(id);

        NbtCompound compound = new NbtCompound();

        compound.putInt("player_count_value", playerCount);
        playerCountValue.put("player_count_info", compound);
        storage.set(id, playerCountValue);
    }

    public static int getPlayerCount(CommandContext<ServerCommandSource> context) {
        DataCommandStorage storage = context.getSource().getServer().getDataCommandStorage();
        Identifier id = new Identifier(WildernessMod.MOD_ID, "player_count");

        NbtCompound playerCountValue = storage.get(id);

        return playerCountValue.getCompound("player_count_info").getInt("player_count_value");
    }

    public static int getTier(int playerCount) {
        if (playerCount <= 0) {
            return 0;
        } else if (playerCount <= 1) {
            return 1;
        } else if (playerCount <= 3) {
            return 2;
        } else if (playerCount <= 7) {
            return 3;
        } else if (playerCount < 15) {
            return 4;
        } else if (playerCount < 31) {
            return 5;
        } else if (playerCount < 63) {
            return 6;
        } else {
            return 7;
        }
    }

    public static LiteralText getPlayerCountPlayerRange(int playerCount) {
        if (playerCount <= 0) {
            return new LiteralText("0 Players");
        } else if (playerCount <= 1) {
            return new LiteralText("1 Player");
        } else if (playerCount <= 3) {
            return new LiteralText("2-3 Players");
        } else if (playerCount <= 7) {
            return new LiteralText("4-7 Players");
        } else if (playerCount <= 15) {
            return new LiteralText("8-15 Players");
        } else if (playerCount <= 31) {
            return new LiteralText("15-31 Players");
        } else if (playerCount <= 63) {
            return new LiteralText("32-63 Players");
        } else {
            return new LiteralText("64+ Players");
        }
    }
    public static LiteralText getTierPlayerRange(int tier) {
        LiteralText txt;
        if (tier == 1) {
            txt =  new LiteralText("1 Player");
        } else if (tier == 2) {
            txt =  new LiteralText("2-3 Players");
        } else if (tier == 3) {
            txt =  new LiteralText("4-7 Players");
        } else if (tier == 4) {
            txt =  new LiteralText("8-15 Players");
        } else if (tier == 5) {
            txt =  new LiteralText("15-31 Players");
        } else if (tier == 6) {
            txt =  new LiteralText("32-63 Players");
        } else if (tier == 7) {
            txt =  new LiteralText("64+ Players");
        } else {
            txt =  new LiteralText("0 Players");
        }

        return (LiteralText) txt.formatted(Formatting.YELLOW);
    }

    public static void addTierToMessage(int tier, LiteralText message) {
        LiteralText formattedTierText = null;

        switch (tier) {
            case 0 -> {
                WildernessMod.LOGGER.info("Will not spawn PVP Loot");
                return;
            }
            case 1 -> formattedTierText = (LiteralText) new LiteralText("Tier " + tier + " ").formatted(Formatting.YELLOW).formatted(Formatting.UNDERLINE);
            case 2 -> formattedTierText = (LiteralText) new LiteralText("Tier " + tier + " ").formatted(Formatting.DARK_GREEN).formatted(Formatting.UNDERLINE);
            case 3 -> formattedTierText = (LiteralText) new LiteralText("Tier " + tier + " ").formatted(Formatting.GREEN).formatted(Formatting.UNDERLINE);
            case 4 -> formattedTierText = (LiteralText) new LiteralText("Tier " + tier + " ").formatted(Formatting.DARK_PURPLE).formatted(Formatting.UNDERLINE);
            case 5 -> formattedTierText = (LiteralText) new LiteralText("Tier " + tier + " ").formatted(Formatting.RED).formatted(Formatting.UNDERLINE);
            case 6 -> formattedTierText = (LiteralText) new LiteralText("Tier " + tier + " ").formatted(Formatting.AQUA).formatted(Formatting.UNDERLINE);
            case 7 -> formattedTierText = (LiteralText) new LiteralText("Tier " + tier + " ").formatted(Formatting.GOLD).formatted(Formatting.UNDERLINE);
            default -> WildernessMod.LOGGER.error("An error occurred when adding tier to broadcasting message!");
        }

        message.append(formattedTierText);

    }


    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated) {
        dispatcher.register(literal("wilderness")
            .then(literal("setPlayerCount")
                .requires(source -> source.hasPermissionLevel(4))
                    .executes(context -> {
                        WildernessMod.LOGGER.info("Setting player count!");

                        int playerCount = context.getSource().getServer().getCurrentPlayerCount();
                        setPlayerCount(context.getSource().getServer().getDataCommandStorage(), playerCount);
                        context.getSource().getPlayer().sendMessage(new LiteralText("Player count set: " + playerCount + ", tier: " + getTier(playerCount)), false);

                        return 0;
                    })
            )
        );

        dispatcher.register(literal("wilderness")
                .then(literal("getPlayerCount")
                        .requires(source -> source.hasPermissionLevel(4))
                        .executes(context -> {
                            WildernessMod.LOGGER.info("Getting stored player count!");

                            int playerCount = getPlayerCount(context);

                            context.getSource().getPlayer().sendMessage(new LiteralText("Player count: " + playerCount), false);

                            return 0;
                        })
                )
        );
    }
}
