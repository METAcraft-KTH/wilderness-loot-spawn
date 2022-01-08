package net.lucasdow.LootCrates.tiers.pvp;

import net.lucasdow.LootCrates.PVPLootCrateBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
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


public class PvPTier1 extends PVPLootCrateBase {
    @Override
    public void fillChest(Inventory chestInventory) {
        ItemStack netherriteChestPlate = new ItemStack(Items.NETHERITE_CHESTPLATE, 1);
        netherriteChestPlate.addEnchantment(Enchantments.PROTECTION, 5);
        netherriteChestPlate.setCustomName(new LiteralText("Titanium Chestplate").formatted(Formatting.RED));

        ItemStack atomSword = new ItemStack(Items.NETHERITE_SWORD, 1);
        atomSword.addEnchantment(Enchantments.SHARPNESS, 6);
        atomSword.setCustomName(new LiteralText("Atom Sword").formatted(Formatting.RED));

        placeItemsIntoChest(Items.DIAMOND_BLOCK, 1, chestInventory, 1, 3);
        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 1, 4);
        placeItemsIntoChest(Items.NETHERITE_BLOCK, 1, chestInventory, 1, 5);
        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 1, 6);
        placeItemsIntoChest(Items.DIAMOND_BLOCK, 1, chestInventory, 1, 7);

        placeItemStackInSlot(2, 2, chestInventory, netherriteChestPlate);
        placeItemsIntoChest(Items.ENCHANTED_GOLDEN_APPLE, 1, chestInventory, 2, 3);
        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 2, 4);
        placeItemStackInSlot(2, 5, chestInventory, structureBlock());
        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 2, 6);
        placeItemsIntoChest(Items.ENCHANTED_GOLDEN_APPLE, 1, chestInventory, 2, 7);
        placeItemStackInSlot(2, 8, chestInventory, atomSword);

        placeItemsIntoChest(Items.DIAMOND_BLOCK, 1, chestInventory, 3, 3);
        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 3, 4);
        placeItemsIntoChest(Items.NETHERITE_BLOCK, 1, chestInventory, 3, 5);
        placeItemsIntoChest(Items.BEACON, 1, chestInventory, 3, 6);
        placeItemsIntoChest(Items.DIAMOND_BLOCK, 1, chestInventory, 3, 7);
    }

    public void createChest() {
        super.createChest();
    }
}
