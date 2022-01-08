package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.lucasdow.LootCrates.PVPLootCrateBase;
import net.minecraft.client.sound.AmbientSoundLoops;
import net.minecraft.client.sound.AmbientSoundPlayer;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class StoryTelling implements Command{

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated) {
        dispatcher.register(literal("wilderness")
                .then(literal("story")
                        .then(argument("page", IntegerArgumentType.integer())
                                .requires(source -> source.hasPermissionLevel(4))
                                .executes(context -> {
                                    LiteralText txt = null;
                                    int page = IntegerArgumentType.getInteger(context, "page");
                                    switch (page) {
                                        case 1 -> txt = new LiteralText("Extraordinary loot awaits those that are brave enough to wander into the wilderness!");
                                        case 2 -> txt = new LiteralText("Treasure drop at PvP-shrine (x: 200 y: -1100) in 30 minutes...");
                                        case 3 -> txt = new LiteralText("The dark shrine rumbles in the distance... Great treasures await those who heed its call for blood.");
                                        case 4 -> txt = new LiteralText("A sense of impending doom fills your heart...");
                                        case 5 -> txt = new LiteralText("...A pulsating sound begins to grow louder around you... are... you... alone?... ");
                                        default -> {
                                            context.getSource().getPlayer().sendMessage(new LiteralText("Invalid page!").formatted(Formatting.RED), false);
                                            return 0;
                                        }
                                    }

                                    context.getSource().getServer().getPlayerManager().broadcast(txt.formatted(Formatting.DARK_RED), MessageType.CHAT, UUID.randomUUID());

                                    return 0;
                                })
                        )
                )
        );
    }
}
