package net.lucasdow.LootCrates.tiers.random;

import net.lucasdow.LootCrates.RandomLootBase;
import net.lucasdow.wildernessmod.WildernessMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.Random;
import java.util.UUID;


public class RandomTier1 extends RandomLootBase {
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
