package com.rpersival.snowdust.blocks;

import com.rpersival.snowdust.items.ItemSettingsGenerator;
import com.rpersival.snowdust.util.ModItemsGroup;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

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
        this.itemSettings = ItemSettingsGenerator.getDefaultItemSettings(ModItemsGroup.DUST_OF_SNOW_GROUP);
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
