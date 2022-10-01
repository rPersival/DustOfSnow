package com.rpersival.snowdust.blocks;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModBlocks {

    public static final ItemBlock TEST_BLOCK = new ItemBlock(
            "test_block",
            new Block(FabricBlockSettings.of(Material.WOOL).strength(0.5f).sounds(BlockSoundGroup.WOOL))
    );

    public static final ItemBlock BREAKABLE_ICE = new ItemBlock(
            "breakable_ice",
            new FragileIceBlock(FabricBlockSettings.of(Material.ICE).strength(0.5f)
                    .slipperiness(0.98f).ticksRandomly()
                    .sounds(BlockSoundGroup.GLASS).nonOpaque(), false)
    );
    public static final ItemBlock CRAUNIUM_ORE = new ItemBlock(
            "craunium_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE)
                    .strength(1.4f).sounds(BlockSoundGroup.STONE).requiresTool())
    );

    public static final ItemBlock DEEPSLATE_CRAUNIUM_ORE = new ItemBlock(
            "deepslate_craunium_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE)
                    .strength(1.6f).sounds(BlockSoundGroup.DEEPSLATE).requiresTool())
    );

    public static List<ItemBlock> getModBlocks() {
        return Arrays.stream(ModBlocks.class.getDeclaredFields())
                .filter(field -> Modifier.isStatic(field.getModifiers()))
                .map(field -> {
                    try {
                        return (ItemBlock) field.get(ModBlocks.class);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public static void putTransparentBlocksToRenderLayerMap() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BREAKABLE_ICE.getBlock(), RenderLayer.getTranslucent());
    }
}
