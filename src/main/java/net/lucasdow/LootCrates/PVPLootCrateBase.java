package net.lucasdow.LootCrates;

import net.lucasdow.wildernessmod.WildernessMod;
import net.lucasdow.wildernessmod.commands.SpawnChestCommand;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.MessageType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class PVPLootCrateBase extends LootCrate {
    public static final int X_SPAWN = 200;
    public static final int Y_SPAWN = 72;
    public static final int Z_SPAWN = -1101;
    public static final BlockPos chestSpawnLocation = new BlockPos(X_SPAWN, Y_SPAWN, Z_SPAWN);

    public ItemStack structureBlock() {
        ItemStack structureBlock = new ItemStack(Items.STRUCTURE_BLOCK, 1);
        structureBlock.setCustomName(new LiteralText("Tier " + this.tier).formatted(Formatting.LIGHT_PURPLE)
                .append(new LiteralText(" Battle Token ").formatted(Formatting.DARK_PURPLE)));
        return structureBlock;
    }

    public void fillChest(Inventory chestInventory) {}

    @Override
    public void createChest() {
        ServerWorld world = source.getWorld();

        BlockState blockState = Blocks.CHEST.getDefaultState();
        world.setBlockState(chestSpawnLocation, blockState);

        Inventory chestInventory = (Inventory) world.getBlockEntity(chestSpawnLocation);

        fillChest(chestInventory);

        Text text = new LiteralText("Tier " + this.tier).formatted(Formatting.GOLD)
                .append(new LiteralText(" PVP LOOT CRATE dropped... May the strongest win!").formatted(Formatting.DARK_RED).formatted(Formatting.BOLD))
                ;

        source.getServer().getPlayerManager().broadcast(text, MessageType.CHAT, UUID.randomUUID());
        source.getWorld().playSound(null, chestSpawnLocation, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.NEUTRAL, 100f, 1f);

        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightning.setPosition(X_SPAWN, Y_SPAWN + 3, Z_SPAWN);
        world.spawnEntity(lightning);
    }
}
