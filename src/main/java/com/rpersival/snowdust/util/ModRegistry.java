package com.rpersival.snowdust.util;

import com.rpersival.snowdust.DustOfSnow;
import com.rpersival.snowdust.blocks.ItemBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import java.util.List;

public class ModRegistry {

    public static void registerItems() {
        List<Pair<Item, String>> modItems = ModItems.getModItems();
        for (Pair<Item, String> item : modItems)
            register(Registry.ITEM, item.getLeft(), item.getRight());
    }

    public static void registerBlocks() {
        List<ItemBlock> modBlocks = ModBlocks.getModBlocks();
        for (ItemBlock block : modBlocks) {
            register(Registry.ITEM, new BlockItem(block.getBlock(),
                    block.getItemSettings()), block.getName());
            register(Registry.BLOCK, block.getBlock(), block.getName());
        }
    }

    public static <T> void register(Registry<T> registry, T object, String name) {
        Registry.register(registry, new Identifier(DustOfSnow.MOD_ID, name), object);
    }
}
