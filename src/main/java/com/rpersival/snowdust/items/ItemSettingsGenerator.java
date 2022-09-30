package com.rpersival.snowdust.items;

import com.rpersival.snowdust.util.ModItemsGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemSettingsGenerator {

    public static Item.Settings getDefaultItemSettings() {
        return new FabricItemSettings().group(ModItemsGroup.DUST_OF_SNOW_GROUP);
    }

    public static Item.Settings getDefaultItemSettings(ItemGroup group) {
        return new FabricItemSettings().group(group);
    }
}
