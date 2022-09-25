package com.rpersival.snowdust.util;

import com.rpersival.snowdust.items.ItemSettingsGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.Pair;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModItems {

    // Items
    public static final Pair<Item, String> CUSTOM = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "custom");

    public static final Pair<Item, String> ANOTHER_ITEM = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "another_item");

    @SuppressWarnings("unchecked")
    public static List<Pair<Item, String>> getModItems() {
        return Arrays.stream(ModItems.class.getDeclaredFields())
                .filter(field -> Modifier.isStatic(field.getModifiers()))
                .map(field -> {
                    try {
                        return (Pair<Item, String>) field.get(ModItems.class);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}
