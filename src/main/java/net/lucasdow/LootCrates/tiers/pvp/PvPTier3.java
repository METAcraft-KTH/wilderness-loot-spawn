package net.lucasdow.LootCrates.tiers.pvp;

import net.lucasdow.LootCrates.PVPLootCrateBase;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;


public class PvPTier3 extends PVPLootCrateBase {
    @Override
    public void fillChest(Inventory chestInventory) {
        placeItemsIntoChest(Items.DIAMOND, 2, chestInventory, 1, 4);
        placeItemsIntoChest(Items.GOLDEN_APPLE, 2, chestInventory, 1, 5);
        placeItemsIntoChest(Items.DIAMOND, 2, chestInventory, 1, 6);


        placeItemsIntoChest(Items.GOLDEN_APPLE, 2, chestInventory, 2, 4);
        placeItemStackInSlot(2, 5, chestInventory, structureBlock());
        placeItemsIntoChest(Items.GOLDEN_APPLE, 2, chestInventory, 2, 6);

        placeItemsIntoChest(Items.DIAMOND, 2, chestInventory, 3, 4);
        placeItemsIntoChest(Items.GOLDEN_APPLE, 2, chestInventory, 3, 5);
        placeItemsIntoChest(Items.DIAMOND, 2, chestInventory, 3, 6);
    }

    public void createChest() {
        super.createChest();
    }
}
