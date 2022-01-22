package net.lucasdow.LootCrates.tiers.pvp;

import net.lucasdow.LootCrates.PVPLootCrateBase;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;

public class PvPTier6 extends PVPLootCrateBase {
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
