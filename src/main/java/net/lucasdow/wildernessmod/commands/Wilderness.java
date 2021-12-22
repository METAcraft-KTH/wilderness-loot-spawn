package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.lucasdow.wildernessmod.CustomLootTables;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTables;
import net.minecraft.network.MessageType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
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

    private static void placeLootChest(ServerCommandSource source, Random random) throws CommandSyntaxException {
        ServerWorld world = source.getWorld();
        int xCoordinate = getXY(MIN_SPAWN_X, MAX_SPAWN_X);
        int zCoordinate = getXY(MIN_SPAWN_Z, MAX_SPAWN_Z);
        int yCoordinate;
        BlockPos blockPos = new BlockPos(xCoordinate, 319, zCoordinate);
        for (yCoordinate = 319; yCoordinate > -64; yCoordinate--) {
            if (world.getBlockState(blockPos).getBlock().equals(Blocks.AIR)) {
                blockPos = new BlockPos(xCoordinate, blockPos.getY()-1, zCoordinate);
            }
            else {
                System.out.println("Place loot chest at... " + xCoordinate + " " + yCoordinate + " " + zCoordinate);

                BlockState blockState = Blocks.CHEST.getDefaultState();
                world.setBlockState(blockPos, blockState);

                Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);
                ItemStack diamondItem = new ItemStack(Items.DIAMOND, 1);
                ItemStack TNTItem = new ItemStack(Items.TNT, 1);
                ItemStack BOEItem = new ItemStack(Items.EXPERIENCE_BOTTLE, 1);
                ItemStack structureItem = new ItemStack(Items.STRUCTURE_BLOCK, 1).setCustomName(new LiteralText("Battle Token"));
                assert blockEntity != null;
                blockEntity.setStack(0, TNTItem);
                blockEntity.setStack(1, TNTItem);
                blockEntity.setStack(7, TNTItem);
                blockEntity.setStack(8, TNTItem);
                blockEntity.setStack(9+0, TNTItem);
                blockEntity.setStack(9+8, TNTItem);
                blockEntity.setStack(9*2+0, TNTItem);
                blockEntity.setStack(9*2+1, TNTItem);
                blockEntity.setStack(9*2+7, TNTItem);
                blockEntity.setStack(9*2+8, TNTItem);
                blockEntity.setStack(4, diamondItem);
                blockEntity.setStack(9+3, diamondItem);
                blockEntity.setStack(9+5, diamondItem);
                blockEntity.setStack(2*9+4, diamondItem);
                blockEntity.setStack(9+1, BOEItem);
                blockEntity.setStack(9+2, BOEItem);
                blockEntity.setStack(9+6, BOEItem);
                blockEntity.setStack(9+7, BOEItem);
                blockEntity.setStack(9+4, structureItem);


                Text text = new LiteralText("Loot crate dropped at: ").formatted(Formatting.AQUA)
                        .append(new LiteralText("X: " + xCoordinate + ", Z: " + zCoordinate + ". ").formatted(Formatting.RED))
                        .append(new LiteralText("(This message will only be displayed once!)").formatted(Formatting.DARK_PURPLE));


                source.getServer().getPlayerManager().broadcast(text, MessageType.CHAT, source.getPlayer().getUuid());
                source.getWorld().playSound(null, blockPos, SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.NEUTRAL, 1f, 1f);
                    //player.sendMessage(new LiteralText("test").formatted(Formatting.AQUA), false);

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

                    Random random = context.getSource().getWorld().getRandom();

                    Wilderness.placeLootChest(context.getSource(), random);

                    return 0;
                })
        );
    }
}