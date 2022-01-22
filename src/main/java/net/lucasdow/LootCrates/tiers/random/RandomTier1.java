package net.lucasdow.LootCrates.tiers.random;

import net.lucasdow.LootCrates.RandomLootBase;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;

public class RandomTier1 extends RandomLootBase  {
    @Override
    public void fillChest(Inventory chestInventory) {
        placeItemsIntoChest(Items.TNT, 2, chestInventory, 2, 4);
        placeItemsIntoChest(Items.TNT, 2, chestInventory, 2, 6);
        placeItemsIntoChest(Items.DIAMOND, 1, chestInventory, 2, 5);
    }

    public void createChest() {
        super.createChest();
    }
}
