package com.rpersival.snowdust.items;

import com.rpersival.snowdust.util.ModMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Pair;
import net.minecraft.util.Rarity;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModItems {

    public static final Pair<Item, String> CUSTOM = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "custom");

    public static final Pair<Item, String> ANOTHER_ITEM = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "another_item");

    public static final Pair<Item, String> TRUE_ICE = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "true_ice");

    public static final Pair<Item, String> ICE_INGOT = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "ice_ingot");

    public static final Pair<ToolItem, String> ICE_AXE = new Pair<>(
            new IceAxeItem(10, -3.0f, ModMaterials.TRUE_ICE_MATERIAL,
                    ItemSettingsGenerator.getDefaultItemSettings(ItemGroup.COMBAT).rarity(Rarity.EPIC)), "ice_axe");

    public static final Pair<Item, String> ICE_STICK = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "ice_stick");

    public static final Pair<Item, String> BREATH_OF_WINTER = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "breath_of_winter");


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
