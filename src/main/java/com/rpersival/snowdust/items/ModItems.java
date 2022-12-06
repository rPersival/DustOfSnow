package com.rpersival.snowdust.items;

import com.rpersival.snowdust.enchantment.ModEnchantments;
import com.rpersival.snowdust.util.ModMaterials;
import com.rpersival.snowdust.util.Register;
import com.rpersival.snowdust.util.Util;
import net.minecraft.block.Blocks;
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
    public static final Pair<Item, String> ICE_STICK = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "ice_stick");

    @Register
    public static final Pair<Item, String> BREATH_OF_WINTER = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "breath_of_winter");

    @Register
    public static final Pair<Item, String> SNOW_ESSENCE = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "snow_essence");

    @Register
    public static final Pair<Item, String> CRAUNIUM_INGOT = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "craunium_ingot");

    @Register
    public static final Pair<Item, String> CRAUNUIM = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "craunium");

    @Register
    public static final Pair<Item, String> DESERT_STICK = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()),"desert_stick");

    @Register
    public static final Pair<Item, String> SEALED_SOULS = new Pair<>(
            new SealedSoulsItem(ItemSettingsGenerator.getDefaultItemSettings()), "sealed_souls");

    @Register
    public static final Pair<Item, String> OKNUM = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()), "oknum");

    @Register
    public static final Pair<Item, String> ETERNAL_FLAME_CRYSTAL = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "eternal_flame_crystal");

    @Register
    public static final Pair<Item, String> BREATH_OF_DESERT = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "breath_of_desert");

    @Register
    public static final Pair<Item, String > OKNUM_INGOT = new Pair<>(
            new Item(ItemSettingsGenerator.getDefaultItemSettings()),"oknum_ingot");


    // Tools
    @Register
    public static final Pair<ToolItem, String> ICE_AXE = new Pair<>(
            new IceAxeItem(6, -2.4f, ModMaterials.TRUE_ICE_MATERIAL,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)), "ice_axe");

    @Register
    public static final Pair<Item, String> SOUL_SICKLE= new Pair<>(
            new SoulSickleItem(ModMaterials.DESERT_MATERIAL, 10, 8,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC)), "soul_sickle");


    // Armor
    @Register
    public static final Pair<ArmorItem, String> TRUE_ICE_HELMET = new Pair<>(
            new AwokenArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.HEAD,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC),
                    ModEnchantments.ICE_REJECTION.getLeft(), Blocks.DIAMOND_BLOCK), "ice_helmet");

    @Register
    public static final Pair<ArmorItem, String> TRUE_ICE_CHESTPLATE = new Pair<>(
            new AwokenArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.CHEST,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC),
                    ModEnchantments.ICE_REJECTION.getLeft(), Blocks.DIAMOND_BLOCK), "ice_chestplate");

    @Register
    public static final Pair<ArmorItem, String> TRUE_ICE_LEGGINS = new Pair<>(
            new AwokenArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.LEGS,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC),
                    ModEnchantments.ICE_REJECTION.getLeft(), Blocks.DIAMOND_BLOCK), "ice_leggins");

    @Register
    public static final Pair<ArmorItem, String> TRUE_ICE_BOOTS = new Pair<>(
            new AwokenArmorItem(ModMaterials.TRUE_ICE_ARMOR_MATERIAL, EquipmentSlot.FEET,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC),
                    ModEnchantments.ICE_REJECTION.getLeft(), Blocks.DIAMOND_BLOCK), "ice_boots");

    @Register
    public static final Pair<ArmorItem, String> CURSED_CROWN = new Pair<>(
            new ArmorItem(ModMaterials.CURSED_CROWN_MATERIAL, EquipmentSlot.HEAD,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.RARE)), "cursed_crown");

    @Register
    public static final Pair<ArmorItem, String> FLAME_HELMET = new Pair<>(
            new AwokenArmorItem(ModMaterials.ETERNAL_FLAME_CRYSTAL_MATERIAL, EquipmentSlot.HEAD,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC),
                    ModEnchantments.FIERY_REJECTION.getLeft(), Blocks.GOLD_BLOCK), "flame_helmet");

    @Register
    public static final Pair<ArmorItem, String> FLAME_CHESTPLATE = new Pair<>(
            new AwokenArmorItem(ModMaterials.ETERNAL_FLAME_CRYSTAL_MATERIAL, EquipmentSlot.CHEST,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC),
                    ModEnchantments.FIERY_REJECTION.getLeft(), Blocks.GOLD_BLOCK), "flame_chestplate");

    @Register
    public static final Pair<ArmorItem, String> FLAME_LEGGINS = new Pair<>(
            new AwokenArmorItem(ModMaterials.ETERNAL_FLAME_CRYSTAL_MATERIAL, EquipmentSlot.LEGS,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC),
                    ModEnchantments.FIERY_REJECTION.getLeft(), Blocks.GOLD_BLOCK), "flame_leggins");

    @Register
    public static final Pair<ArmorItem, String> FLAME_BOOTS = new Pair<>(
            new AwokenArmorItem(ModMaterials.ETERNAL_FLAME_CRYSTAL_MATERIAL, EquipmentSlot.FEET,
                    ItemSettingsGenerator.getDefaultItemSettings().rarity(Rarity.EPIC),
                    ModEnchantments.FIERY_REJECTION.getLeft(), Blocks.GOLD_BLOCK), "flame_boots");


    @SuppressWarnings("unchecked")
    public static List<Pair<Item, String>> getModItems() {
        return (List<Pair<Item, String>>)
                Util.getRegistryFields(ModItems.class, TRUE_ICE.getClass());
    }
}
