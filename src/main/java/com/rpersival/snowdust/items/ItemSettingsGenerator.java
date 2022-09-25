package com.rpersival.snowdust.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemSettingsGenerator {

    public static Item.Settings getDefaultItemSettings() {
        return new FabricItemSettings().group(ItemGroup.MISC);
    }

    public static Item.Settings getDefaultItemSettings(ItemGroup group) {
        return new FabricItemSettings().group(group);
    }
}
