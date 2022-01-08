package net.lucasdow.LootCrates.tiers.random;

import net.lucasdow.LootCrates.RandomLootBase;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;

public class RandomTier2 extends RandomLootBase {
    @Override
    public void fillChest(Inventory chestInventory) {
        placeItemsIntoChest(Items.END_CRYSTAL, 1, chestInventory, 1, 4);
        placeItemsIntoChest(Items.DIAMOND, 16, chestInventory, 1, 5);
        placeItemsIntoChest(Items.SHULKER_SHELL, 1, chestInventory, 1, 6);

        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 2, 3);
        placeItemsIntoChest(Items.NETHERITE_INGOT, 1, chestInventory, 2, 4);
        placeItemsIntoChest(Items.ENCHANTED_GOLDEN_APPLE, 1, chestInventory, 2, 5);
        placeItemsIntoChest(Items.NETHERITE_INGOT, 1, chestInventory, 2, 6);
        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 2, 7);

        placeItemsIntoChest(Items.SHULKER_SHELL, 1, chestInventory, 3, 4);
        placeItemsIntoChest(Items.DIAMOND, 16, chestInventory, 3, 5);
        placeItemsIntoChest(Items.END_CRYSTAL, 1, chestInventory, 3, 6);
    }

    public void createChest() {
        super.createChest();
    }
}
