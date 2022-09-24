package com.rpersival.snowdust.util;

import com.rpersival.snowdust.DustOfSnow;
import net.minecraft.item.Item;

import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import java.util.List;

public class ModRegistry {

    public static void registerItems() {
        List<Pair<Item, String>> modItems = ModItems.getModItems();
        for (Pair<Item, String> item : modItems)
            register(Registry.ITEM, item);
    }

    public static <T> void register(Registry<T> registry, Pair<T, String> object) {
        Registry.register(registry, new Identifier(DustOfSnow.MOD_ID, object.getRight()), object.getLeft());
    }
}
