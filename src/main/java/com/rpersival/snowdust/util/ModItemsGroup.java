package com.rpersival.snowdust.util;

import com.rpersival.snowdust.DustOfSnow;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemsGroup{

    public static final ItemGroup DUST_OF_SNOW_GROUP = FabricItemGroupBuilder.build(new Identifier(DustOfSnow.MOD_ID,
                    "dustofsnow"), () -> new ItemStack(Blocks.ICE));

}
