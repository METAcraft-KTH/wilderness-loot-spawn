package net.lucasdow.LootCrates.tiers.pvp;

import net.lucasdow.LootCrates.PVPLootCrateBase;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;


public class PvPTier5 extends PVPLootCrateBase {
    @Override
    public void fillChest(Inventory chestInventory) {
        placeItemsIntoChest(Items.END_CRYSTAL, 1, chestInventory, 1, 3);
        placeItemsIntoChest(Items.GOLDEN_APPLE, 8, chestInventory, 1, 4);
        placeItemsIntoChest(Items.NETHERITE_INGOT, 1, chestInventory, 1, 5);
        placeItemsIntoChest(Items.GOLDEN_APPLE, 8, chestInventory, 1, 6);
        placeItemsIntoChest(Items.END_CRYSTAL, 1, chestInventory, 1, 7);


        placeItemsIntoChest(Items.DIAMOND, 16, chestInventory, 2, 3);
        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 2, 4);
        placeItemStackInSlot(2, 5, chestInventory, structureBlock());
        placeItemsIntoChest(Items.SHULKER_SHELL, 2, chestInventory, 2, 6);
        placeItemsIntoChest(Items.DIAMOND, 16, chestInventory, 2, 7);

        placeItemsIntoChest(Items.END_CRYSTAL, 1, chestInventory, 3, 3);
        placeItemsIntoChest(Items.TNT, 32, chestInventory, 3, 4);
        placeItemsIntoChest(Items.NETHERITE_INGOT, 1, chestInventory, 3, 5);
        placeItemsIntoChest(Items.TNT, 32, chestInventory, 3, 6);
        placeItemsIntoChest(Items.END_CRYSTAL, 1, chestInventory, 3, 7);
    }

    public void createChest() {
        super.createChest();
    }
}
