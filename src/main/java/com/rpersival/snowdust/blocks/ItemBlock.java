package com.rpersival.snowdust.blocks;

import com.rpersival.snowdust.items.ItemSettingsGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBlock {

    private final String name;
    private final Block block;
    private final Item.Settings itemSettings;

    public ItemBlock(String name, Block block, Item.Settings itemSettings) {
        this.name = name;
        this.block = block;
        this.itemSettings = itemSettings;
    }

    public ItemBlock(String name, Block block) {
        this.name = name;
        this.block = block;
        this.itemSettings = ItemSettingsGenerator.getDefaultItemSettings(ItemGroup.BUILDING_BLOCKS);
    }

    public String getName() {
        return name;
    }

    public Block getBlock() {
        return block;
    }

    public Item.Settings getItemSettings() {
        return itemSettings;
    }
}
