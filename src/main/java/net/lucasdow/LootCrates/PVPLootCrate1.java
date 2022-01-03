package net.lucasdow.LootCrates;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.inventory.Inventory;
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

public class PVPLootCrate1 extends PVPLootCrateBase {
    public static void placeLootChest(ServerCommandSource source, Random random) {
        ServerWorld world = source.getWorld();
        BlockPos blockPos = new BlockPos(X_SPAWN, Y_SPAWN, Z_SPAWN);

        BlockState blockState = Blocks.CHEST.getDefaultState();
        world.setBlockState(blockPos, blockState);

        Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);

        ItemStack diamonItem = new ItemStack(Items.DIAMOND, 1);
        ItemStack beaconItem = new ItemStack(Items.BEACON, 1);
        ItemStack netherriteIngotItem = new ItemStack(Items.NETHERITE_INGOT, 1);
        ItemStack BOEItem = new ItemStack(Items.EXPERIENCE_BOTTLE, 32);
        ItemStack goldenAppleItem = new ItemStack(Items.GOLDEN_APPLE, 4);
        ItemStack structureItem = new ItemStack(Items.STRUCTURE_BLOCK, 1).setCustomName(Text.of("Battle Token"));

        assert blockEntity != null;

        for (int i = 0; i < 27; i++) {
            blockEntity.setStack(i, diamonItem.copy());
        }

        placeItemStackInSlot(2, 5, blockEntity, structureItem);
        placeItemStackInSlot(1, 5, blockEntity, beaconItem);

        placeItemStackInSlot(1, 1, blockEntity, goldenAppleItem);
        placeItemStackInSlot(3, 1, blockEntity, goldenAppleItem);
        placeItemStackInSlot(1, 9, blockEntity, goldenAppleItem);
        placeItemStackInSlot(3, 9, blockEntity, goldenAppleItem);

        placeItemStackInSlot(1, 4, blockEntity, netherriteIngotItem);
        placeItemStackInSlot(1, 6, blockEntity, netherriteIngotItem);
        placeItemStackInSlot(3, 4, blockEntity, netherriteIngotItem);
        placeItemStackInSlot(3, 6, blockEntity, netherriteIngotItem);

        placeItemStackInSlot(2, 4, blockEntity, BOEItem);
        placeItemStackInSlot(2, 6, blockEntity, BOEItem);
        placeItemStackInSlot(3, 5, blockEntity, BOEItem);

        Text text = new LiteralText("PVP LOOT CRATE dropped... May the strongest win!").formatted(Formatting.DARK_RED);

        source.getServer().getPlayerManager().broadcast(text, MessageType.CHAT, UUID.randomUUID());
        source.getWorld().playSound(null, blockPos, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.NEUTRAL, 100f, 1f);

        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightning.setPosition(X_SPAWN, Y_SPAWN + 3, Z_SPAWN);
        world.spawnEntity(lightning);
    }
}
