package net.lucasdow.wildernessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Random;

public class SpawnChestCommand implements Command {

    public static void placeItemStackInSlot(int row, int column, Inventory block, ItemStack stack) {
        int slot = (row-1) * 9 + (column - 1);

        assert slot >= 0 && slot < 27;

        block.setStack(slot, stack.copy());
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, Boolean dedicated) {

    }
}
