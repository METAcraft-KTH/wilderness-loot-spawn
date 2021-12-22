package net.lucasdow.wildernessmod;

import com.google.common.collect.Sets;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.Set;

public class CustomLootTables {
    private static final Set<Identifier> LOOT_TABLES = Sets.newHashSet();
    public static final Identifier WILDERNESS_LOOT;

    public CustomLootTables() {

    }

    private static Identifier register(String id) {
        return registerLootTable(new Identifier(WildernessMod.MOD_ID, id));
    }

    private static Identifier registerLootTable(Identifier id) {
        if (LOOT_TABLES.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }

    static {
        WILDERNESS_LOOT = register("loot_tables/chests/wilderness_loot");
    }
}
