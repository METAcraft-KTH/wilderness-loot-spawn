package net.lucasdow.LootCrates;

import net.lucasdow.wildernessmod.WildernessMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.network.MessageType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class RandomLootBase extends LootCrate {
    public final int MIN_SPAWN_X = -7500;
    public final int MAX_SPAWN_X = 7500;
    public final int MIN_SPAWN_Z = -10000;
    public final int MAX_SPAWN_Z = -500;
    public final int BUILD_LIMIT = 319;

    public int getXY(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int getYCoordinate(int x, int z) {
        int yCoordinate;
        BlockPos blockPos = new BlockPos(x, 319, z);
        for (yCoordinate = 319; yCoordinate > -64; yCoordinate--) {
            if (world.getBlockState(blockPos).getBlock().equals(Blocks.AIR)) {
                blockPos = new BlockPos(x, blockPos.getY() - 1, z);
            } else {
                return yCoordinate+1;
            }
        }

        WildernessMod.LOGGER.info("Could not place chest at surface, placing at y=64");

        return 64;
    }

    public void fillChest(Inventory chestInventory) {}

    @Override
    public void createChest() {
        int xCoordinate = getXY(MIN_SPAWN_X, MAX_SPAWN_X);
        int zCoordinate = getXY(MIN_SPAWN_Z, MAX_SPAWN_Z);
        int yCoordinate = getYCoordinate(xCoordinate, zCoordinate);

        BlockPos finalChestPos = new BlockPos(xCoordinate, yCoordinate, zCoordinate);

        BlockState blockState = Blocks.CHEST.getDefaultState();
        world.setBlockState(finalChestPos, blockState);
        Inventory chestInventory = (Inventory) world.getBlockEntity(finalChestPos);

        fillChest(chestInventory);

        WildernessMod.LOGGER.info("Placing loot chest at... " + xCoordinate + " " + yCoordinate + " " + zCoordinate);

        Text text = new LiteralText("Tier " + this.tier).formatted(Formatting.GOLD)
                .append(new LiteralText(" Loot crate dropped at: ").formatted(Formatting.AQUA))
                .append(new LiteralText("X: " + xCoordinate + ", Z: " + zCoordinate + ". ").formatted(Formatting.RED))
                .append(new LiteralText("(This message will only be displayed once!)").formatted(Formatting.DARK_PURPLE));


        source.getServer().getPlayerManager().broadcast(text, MessageType.CHAT, UUID.randomUUID());
        source.getWorld().playSound(null, finalChestPos, SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.NEUTRAL, 1f, 1f);
    }
}
