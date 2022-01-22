package net.lucasdow.LootCrates.tiers.pvp;

import net.lucasdow.LootCrates.PVPLootCrateBase;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;


public class PvPTier1 extends PVPLootCrateBase {
    @Override
    public void fillChest(Inventory chestInventory) {
        placeItemsIntoChest(Items.TNT, 2, chestInventory, 1, 5);

        placeItemsIntoChest(Items.DIAMOND, 1, chestInventory, 2, 4);
        placeItemStackInSlot(2, 5, chestInventory, structureBlock());
        placeItemsIntoChest(Items.DIAMOND, 1, chestInventory, 2, 6);

        placeItemsIntoChest(Items.TNT, 2, chestInventory, 3, 5);
    }

    public void createChest() {
        super.createChest();
    }
}
