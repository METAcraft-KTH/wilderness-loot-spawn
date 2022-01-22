package net.lucasdow.LootCrates.tiers.random;

import net.lucasdow.LootCrates.RandomLootBase;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;

public class RandomTier2 extends RandomLootBase  {
    @Override
    public void fillChest(Inventory chestInventory) {
        placeItemsIntoChest(Items.TNT, 2, chestInventory, 2, 3);
        placeItemsIntoChest(Items.TNT, 2, chestInventory, 2, 7);
        placeItemsIntoChest(Items.TNT, 2, chestInventory, 1, 5);
        placeItemsIntoChest(Items.TNT, 2, chestInventory, 3, 5);

        placeItemsIntoChest(Items.DIAMOND, 1, chestInventory, 2, 6);
        placeItemsIntoChest(Items.DIAMOND, 1, chestInventory, 2, 4);

        placeItemsIntoChest(Items.GOLDEN_APPLE, 1, chestInventory, 2, 5);
    }

    public void createChest() {
        super.createChest();
    }
}
