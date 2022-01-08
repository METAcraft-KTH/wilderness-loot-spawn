package net.lucasdow.LootCrates.tiers.pvp;

import net.lucasdow.LootCrates.PVPLootCrateBase;
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

public class PvPTier2 extends PVPLootCrateBase {
    @Override
    public void fillChest(Inventory chestInventory) {
        placeItemsIntoChest(Items.NETHERITE_INGOT, 1, chestInventory, 1, 4);
        placeItemsIntoChest(Items.SHULKER_BOX, 1, chestInventory, 1, 5);
        placeItemsIntoChest(Items.NETHERITE_INGOT, 1, chestInventory, 1, 6);

        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 2, 2);
        placeItemsIntoChest(Items.GOLDEN_APPLE, 16, chestInventory, 2, 3);
        placeItemsIntoChest(Items.DIAMOND, 32, chestInventory, 2, 4);
        placeItemStackInSlot(2, 5, chestInventory, structureBlock());
        placeItemsIntoChest(Items.ENCHANTED_GOLDEN_APPLE, 1, chestInventory, 2, 6);
        placeItemsIntoChest(Items.GOLDEN_APPLE, 16, chestInventory, 2, 7);
        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 2, 8);

        placeItemsIntoChest(Items.NETHERITE_INGOT, 1, chestInventory, 3, 4);
        placeItemsIntoChest(Items.SHULKER_BOX, 1, chestInventory, 3, 5);
        placeItemsIntoChest(Items.NETHERITE_INGOT, 1, chestInventory, 3, 6);
    }

    public void createChest() {
        super.createChest();
    }
}
