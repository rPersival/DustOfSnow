package com.rpersival.snowdust.items;

import com.rpersival.snowdust.util.ModMaterials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Pair;
import net.minecraft.util.Rarity;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModItems {

    //items
    public static final Pair<Item, String> CUSTOM = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "custom");

    public static final Pair<Item, String> ANOTHER_ITEM = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "another_item");

    public static final Pair<Item, String> TRUE_ICE = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "true_ice");

    public static final Pair<Item, String> ICE_INGOT = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "ice_ingot");

    public static final Pair<Item, String> ICE_STICK = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "ice_stick");

    public static final Pair<Item, String> BREATH_OF_WINTER = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "breath_of_winter");

    public static final Pair<Item, String> SNOW_ESSENCE = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "snow_essence");

    public static final Pair<Item, String> CRAUNUIM = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "craunium");


    //tools
    public static final Pair<ToolItem, String> ICE_AXE = new Pair<>(
            new IceAxeItem(6, -2.4f, ModMaterials.TRUE_ICE_MATERIAL,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)), "ice_axe");


    //armor
    public static final Pair<Item, String> TRUE_ICE_HELMET = new Pair<>(
            new ArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.HEAD, ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)),
            "true_ice_helmet");

    public static final Pair<Item, String> TRUE_ICE_CHESTPLATE = new Pair<>(
            new ArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.CHEST, ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)),
            "true_ice_chestplate");

    public static final Pair<Item, String> TRUE_ICE_LEGGINS = new Pair<>(
            new ArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.LEGS, ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)),
            "true_ice_leggins");

    public static final Pair<Item, String> TRUE_ICE_BOOTS = new Pair<>(
            new ArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.FEET, ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)),
            "true_ice_boots");



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
