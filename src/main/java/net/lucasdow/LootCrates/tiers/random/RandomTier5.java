package net.lucasdow.LootCrates.tiers.random;

import net.lucasdow.LootCrates.RandomLootBase;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;

public class RandomTier5 extends RandomLootBase {
    @Override
    public void fillChest(Inventory chestInventory) {
        placeItemsIntoChest(Items.TNT, 8, chestInventory, 1, 3);
        placeItemsIntoChest(Items.GOLDEN_APPLE, 8, chestInventory, 1, 4);
        placeItemsIntoChest(Items.DIAMOND, 2, chestInventory, 1, 5);
        placeItemsIntoChest(Items.GOLDEN_APPLE, 8, chestInventory, 1, 6);
        placeItemsIntoChest(Items.TNT, 8, chestInventory, 1, 7);


        placeItemsIntoChest(Items.SHULKER_SHELL, 1, chestInventory, 2, 3);
        placeItemsIntoChest(Items.DIAMOND, 2, chestInventory, 2, 4);
        placeItemsIntoChest(Items.NETHERITE_INGOT, 1, chestInventory, 2, 5);
        placeItemsIntoChest(Items.DIAMOND, 2, chestInventory, 2, 6);
        placeItemsIntoChest(Items.SHULKER_SHELL, 1, chestInventory, 2, 7);

        placeItemsIntoChest(Items.TNT, 8, chestInventory, 3, 3);
        placeItemsIntoChest(Items.GOLDEN_APPLE, 8, chestInventory, 3, 4);
        placeItemsIntoChest(Items.DIAMOND, 2, chestInventory, 3, 5);
        placeItemsIntoChest(Items.GOLDEN_APPLE, 8, chestInventory, 3, 6);
        placeItemsIntoChest(Items.TNT, 8, chestInventory, 3, 7);
    }

    public void createChest() {
        super.createChest();
    }
}
