package net.lucasdow.LootCrates;

import net.lucasdow.wildernessmod.commands.SpawnChestCommand;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;

import java.util.Random;

public class LootCrate  {
    public ServerCommandSource source;
    public ServerWorld world;
    public Random random;
    int tier;

    public void setSource(ServerCommandSource source, Random random) {
        this.source = source;
        this.random = random;
        this.world = source.getWorld();
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void createChest() {}

    public ItemStack generateItemStack(Item item, int amount) {
        return new ItemStack(item, amount);
    }

    public void placeItemStackInSlot(int row, int column, Inventory block, ItemStack stack) {
        int slot = (row-1) * 9 + (column - 1);

        assert slot >= 0 && slot < 27;

        block.setStack(slot, stack.copy());
    }

    public void placeItemsIntoChest(Item item, int amount, Inventory chestInventory, int row, int column) {
        ItemStack stack = generateItemStack(item, amount);

        placeItemStackInSlot(row, column, chestInventory, stack);
    }
}
