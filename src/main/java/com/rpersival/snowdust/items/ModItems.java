package com.rpersival.snowdust.items;

import com.rpersival.snowdust.util.ModMaterials;
import com.rpersival.snowdust.util.Register;
import com.rpersival.snowdust.util.Util;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Pair;
import net.minecraft.util.Rarity;

import java.util.List;

public class ModItems {

    // Items
    @Register
    public static final Pair<Item, String> TRUE_ICE = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "true_ice");

    @Register
    public static final Pair<Item, String> ICE_INGOT = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "ice_ingot");

    @Register
    public static final Pair<Item, String> ICE_STICK = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "ice_stick");

    @Register
    public static final Pair<Item, String> BREATH_OF_WINTER = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "breath_of_winter");

    @Register
    public static final Pair<Item, String> SNOW_ESSENCE = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "snow_essence");

    @Register
    public static final Pair<Item, String> CRAUNUIM = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "craunium");


    // Tools

    @Register
    public static final Pair<ToolItem, String> ICE_AXE = new Pair<>(
            new IceAxeItem(6, -2.4f, ModMaterials.TRUE_ICE_MATERIAL,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)), "ice_axe");


    // Armor

    @Register
    public static final Pair<ArmorItem, String> TRUE_ICE_HELMET = new Pair<>(
            new ArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.HEAD,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)), "ice_helmet");

    @Register
    public static final Pair<ArmorItem, String> TRUE_ICE_CHESTPLATE = new Pair<>(
            new ArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.CHEST,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)), "ice_chestplate");

    @Register
    public static final Pair<ArmorItem, String> TRUE_ICE_LEGGINS = new Pair<>(
            new ArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.LEGS,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)), "ice_leggins");

    @Register
    public static final Pair<ArmorItem, String> TRUE_ICE_BOOTS = new Pair<>(
            new ArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.FEET,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)), "ice_boots");



    @SuppressWarnings("unchecked")
    public static List<Pair<Item, String>> getModItems() {
        return (List<Pair<Item, String>>)
                Util.getRegistryFields(ModItems.class, TRUE_ICE.getClass());
    }
}
