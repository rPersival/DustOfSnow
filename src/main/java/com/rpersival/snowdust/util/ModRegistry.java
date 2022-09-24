package com.rpersival.snowdust.util;

import com.rpersival.snowdust.DustOfSnow;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import net.minecraft.item.ItemGroup;
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

    public static void registerBlocks() {
        List<Pair<Block, String>> modBlocks = ModBlocks.getModBlocks();
        for (Pair<Block, String> block : modBlocks) {
            register(Registry.ITEM, new BlockItem(block.getLeft(),
                    new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)), block.getRight());
            register(Registry.BLOCK, block);
        }
    }

    public static <T> void register(Registry<T> registry, Pair<T, String> object) {
        Registry.register(registry, new Identifier(DustOfSnow.MOD_ID, object.getRight()), object.getLeft());
    }
    public static <T> void register(Registry<T> registry, T object, String name) {
        Registry.register(registry, new Identifier(DustOfSnow.MOD_ID, name), object);
    }
}
