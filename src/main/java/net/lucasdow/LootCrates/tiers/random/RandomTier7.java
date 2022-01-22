package net.lucasdow.LootCrates.tiers.random;

import net.lucasdow.LootCrates.RandomLootBase;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;


public class RandomTier7 extends RandomLootBase {
    @Override
    public void fillChest(Inventory chestInventory) {
        ItemStack elytra = new ItemStack(Items.ELYTRA, 1);
        elytra.addEnchantment(Enchantments.MENDING, 1);
        elytra.addEnchantment(Enchantments.UNBREAKING, 3);
        elytra.setCustomName(new LiteralText("Premium Wings").formatted(Formatting.GOLD));

        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 1, 4);
        placeItemsIntoChest(Items.DIAMOND, 16, chestInventory, 1, 5);
        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 1, 6);

        placeItemsIntoChest(Items.ENCHANTED_GOLDEN_APPLE, 1, chestInventory, 2, 3);
        placeItemsIntoChest(Items.DIAMOND, 16, chestInventory, 2, 4);
        placeItemStackInSlot(2, 5, chestInventory, elytra);
        placeItemsIntoChest(Items.DIAMOND, 16, chestInventory, 2, 6);
        placeItemsIntoChest(Items.ENCHANTED_GOLDEN_APPLE, 1, chestInventory, 2, 7);

        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 3, 4);
        placeItemsIntoChest(Items.DIAMOND, 16, chestInventory, 3, 5);
        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 3, 6);
    }

    public void createChest() {
        super.createChest();
    }
}
