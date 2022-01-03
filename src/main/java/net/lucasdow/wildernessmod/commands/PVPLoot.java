package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.lucasdow.LootCrates.PVPLootCrate1;
import net.lucasdow.LootCrates.PVPLootCrate2;
import net.lucasdow.wildernessmod.WildernessMod;
import net.minecraft.block.BeaconBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StructureBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.Random;
import java.util.UUID;

import static net.minecraft.server.command.CommandManager.literal;

public class PVPLoot implements Command {

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated) {
        dispatcher.register(literal("wilderness")
            .then(literal("spawnPVPLoot")
                .then(literal("type-one")
                    .requires(source -> source.hasPermissionLevel(4))
                    .executes(context -> {
                        WildernessMod.LOGGER.info("Spawning PVP-loot!");

                        Random random = context.getSource().getWorld().getRandom();

                        PVPLootCrate1.placeLootChest(context.getSource(), random);

                        return 0;
                    })
                )
            )
        );

        dispatcher.register(literal("wilderness")
            .then(literal("spawnPVPLoot")
                .then(literal("type-two")
                    .requires(source -> source.hasPermissionLevel(4))
                    .executes(context -> {
                        WildernessMod.LOGGER.info("Spawning PVP-loot!");

                        Random random = context.getSource().getWorld().getRandom();

                        PVPLootCrate2.placeLootChest(context.getSource(), random);

                        return 0;
                    })
                )
            )
        );
    }
}
