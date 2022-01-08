package net.lucasdow.LootCrates.tiers.random;

import net.lucasdow.LootCrates.RandomLootBase;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;

public class RandomTier4 extends RandomLootBase {
    @Override
    public void fillChest(Inventory chestInventory) {
        placeItemsIntoChest(Items.TNT, 8, chestInventory, 1, 4);
        placeItemsIntoChest(Items.DIAMOND, 1, chestInventory, 1, 5);
        placeItemsIntoChest(Items.TNT, 8, chestInventory, 1, 6);

        placeItemsIntoChest(Items.GOLDEN_APPLE, 4, chestInventory, 2, 3);
        placeItemsIntoChest(Items.DIAMOND, 1, chestInventory, 2, 4);
        placeItemsIntoChest(Items.OBSIDIAN, 32, chestInventory, 2, 5);
        placeItemsIntoChest(Items.DIAMOND, 1, chestInventory, 2, 6);
        placeItemsIntoChest(Items.GOLDEN_APPLE, 4, chestInventory, 2, 7);

        placeItemsIntoChest(Items.TNT, 8, chestInventory, 3, 4);
        placeItemsIntoChest(Items.DIAMOND, 1, chestInventory, 3, 5);
        placeItemsIntoChest(Items.TNT, 8, chestInventory, 3, 6);
    }

    public void createChest() {
        super.createChest();
    }
}
