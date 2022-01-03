package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.lucasdow.wildernessmod.WildernessMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.Random;
import java.util.UUID;

import static net.minecraft.server.command.CommandManager.literal;

public class RandomLoot extends SpawnChestCommand {
    static int MIN_SPAWN_X = -2500;
    static int MAX_SPAWN_X = 2500;
    static int MIN_SPAWN_Z = -3500;
    static int MAX_SPAWN_Z = -500;

    public static int getXY(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static void placeLootChest(ServerCommandSource source, Random random) {
        ServerWorld world = source.getWorld();

        // Ensure that 12 or more players are playing

        if (world.getPlayers().toArray().length < 15) {
            WildernessMod.LOGGER.warn("Not enough players at the moment to spawn random loot");
            return;
        }

        int xCoordinate = getXY(MIN_SPAWN_X, MAX_SPAWN_X);
        int zCoordinate = getXY(MIN_SPAWN_Z, MAX_SPAWN_Z);
        int yCoordinate;
        BlockPos blockPos = new BlockPos(xCoordinate, 319, zCoordinate);
        for (yCoordinate = 319; yCoordinate > -64; yCoordinate--) {
            if (world.getBlockState(blockPos).getBlock().equals(Blocks.AIR)) {
                blockPos = new BlockPos(xCoordinate, blockPos.getY()-1, zCoordinate);
            }
            else {
                WildernessMod.LOGGER.info("Placing loot chest at... " + xCoordinate + " " + yCoordinate + " " + zCoordinate);

                BlockPos finalChestPos = new BlockPos(xCoordinate, yCoordinate+1, zCoordinate);

                BlockState blockState = Blocks.CHEST.getDefaultState();
                world.setBlockState(finalChestPos, blockState);

                Inventory chestInventory = (Inventory) world.getBlockEntity(finalChestPos);
                ItemStack diamondItem = new ItemStack(Items.DIAMOND, 1);
                ItemStack TNTItem = new ItemStack(Items.TNT, 8);
                ItemStack BOEItem = new ItemStack(Items.EXPERIENCE_BOTTLE, 32);
                ItemStack goldenApple = new ItemStack(Items.GOLDEN_APPLE, 1);
                ItemStack lavaBucketItem = new ItemStack(Items.LAVA_BUCKET, 1);
                ItemStack ironIngotItem = new ItemStack(Items.IRON_INGOT, 16);

                assert chestInventory != null;

                placeItemStackInSlot(1, 4, chestInventory, ironIngotItem.copy());
                placeItemStackInSlot(1, 6, chestInventory, ironIngotItem.copy());
                placeItemStackInSlot(3, 4, chestInventory, ironIngotItem.copy());
                placeItemStackInSlot(3, 6, chestInventory, ironIngotItem.copy());

                placeItemStackInSlot(1, 5, chestInventory, TNTItem.copy());
                placeItemStackInSlot(2, 4, chestInventory, TNTItem.copy());
                placeItemStackInSlot(2, 6, chestInventory, TNTItem.copy());
                placeItemStackInSlot(3, 5, chestInventory, TNTItem.copy());

                placeItemStackInSlot(2, 3, chestInventory, BOEItem.copy());
                placeItemStackInSlot(2, 7, chestInventory, BOEItem.copy());

                placeItemStackInSlot(2, 2, chestInventory, diamondItem.copy());
                placeItemStackInSlot(2, 8, chestInventory, diamondItem.copy());

                placeItemStackInSlot(2, 5, chestInventory, lavaBucketItem.copy());


                Text text = new LiteralText("Loot crate dropped at: ").formatted(Formatting.AQUA)
                        .append(new LiteralText("X: " + xCoordinate + ", Z: " + zCoordinate + ". ").formatted(Formatting.RED))
                        .append(new LiteralText("(This message will only be displayed once!)").formatted(Formatting.DARK_PURPLE));


                source.getServer().getPlayerManager().broadcast(text, MessageType.CHAT, UUID.randomUUID());
                source.getWorld().playSound(null, blockPos, SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.NEUTRAL, 1f, 1f);

                break;
            }
        }

        //LootableContainerBlockEntity.setLootTable(world, random, bp, LootTables.SPAWN_BONUS_CHEST);
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated) {
        dispatcher.register(literal("wilderness")
            .then(literal("spawnRandomLoot")
                .requires(source -> source.hasPermissionLevel(4))
                .executes(context -> {
                    WildernessMod.LOGGER.info("Placing random loot!");

                    Random random = context.getSource().getWorld().getRandom();

                    RandomLoot.placeLootChest(context.getSource(), random);

                    return 0;
                })
            )
        );
    }
}