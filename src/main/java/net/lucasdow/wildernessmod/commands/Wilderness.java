package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.lucasdow.wildernessmod.CustomLootTables;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTables;
import net.minecraft.network.MessageType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.Random;
import java.util.UUID;

interface Command {
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated);
}

public class Wilderness implements Command {
    static int MIN_SPAWN_X = -2500;
    static int MAX_SPAWN_X = 2500;
    static int MIN_SPAWN_Z = -2500;
    static int MAX_SPAWN_Z = -500;

    public static int getXY(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static void placeLootChest(ServerWorld world, Random random) {
        int xCoordinate = getXY(MIN_SPAWN_X, MAX_SPAWN_X);
        int zCoordinate = getXY(MIN_SPAWN_Z, MAX_SPAWN_Z);
        int yCoordinate;
        BlockPos blockPos = new BlockPos(xCoordinate, 319, zCoordinate);
        for (yCoordinate = 319; yCoordinate > -64; yCoordinate--) {
            System.out.println("i: " + yCoordinate + " y" + blockPos.getY() + " block: " + world.getBlockState(blockPos).getBlock() );
            if (world.getBlockState(blockPos).getBlock().equals(Blocks.AIR)) {
                blockPos = new BlockPos(xCoordinate, blockPos.getY()-1, zCoordinate);
            }
            else {
                System.out.println("Place loot chest at... " + xCoordinate + " " + yCoordinate + " " + zCoordinate);

                BlockState blockState = Blocks.CHEST.getDefaultState();
                world.setBlockState(blockPos, blockState);

                Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);
                ItemStack stack = new ItemStack(Items.DIAMOND, 10);
                assert blockEntity != null;
                blockEntity.setStack(0, stack);

                for (PlayerEntity player : world.getServer().getPlayerManager().getPlayerList()) {
                    player.sendMessage(new LiteralText("test"), false);
                }

                break;
            }
        }

        //LootableContainerBlockEntity.setLootTable(world, random, bp, LootTables.SPAWN_BONUS_CHEST);
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated) {
        dispatcher.register(CommandManager.literal("wilderness")
                .requires(source -> source.hasPermissionLevel(4))
                .executes(context -> {
                    System.out.println("Placing random loot!");

                    ServerWorld world = context.getSource().getWorld();
                    Random random = context.getSource().getWorld().getRandom();

                    Wilderness.placeLootChest(world, random);

                    return 0;
                })
        );
    }
}