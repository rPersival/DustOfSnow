package com.rpersival.snowdust.blocks;

import com.rpersival.snowdust.util.Register;
import com.rpersival.snowdust.util.Util;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;

import java.util.List;

public class ModBlocks {

    @Register
    public static final ItemBlock TEST_BLOCK = new ItemBlock(
            "test_block",
            new Block(FabricBlockSettings.of(Material.WOOL).strength(0.5f).sounds(BlockSoundGroup.WOOL))
    );

    @Register
    public static final ItemBlock BREAKABLE_ICE = new ItemBlock(
            "breakable_ice",
            new FragileIceBlock(FabricBlockSettings.of(Material.ICE).strength(0.5f)
                    .slipperiness(0.98f).ticksRandomly()
                    .sounds(BlockSoundGroup.GLASS).nonOpaque(), false)
    );

    @Register
    public static final ItemBlock CRAUNIUM_ORE = new ItemBlock(
            "craunium_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE)
                    .strength(1.4f).sounds(BlockSoundGroup.STONE).requiresTool())
    );

    @Register
    public static final ItemBlock DEEPSLATE_CRAUNIUM_ORE = new ItemBlock(
            "deepslate_craunium_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE)
                    .strength(1.6f).sounds(BlockSoundGroup.DEEPSLATE).requiresTool())
    );

    public static List<ItemBlock> getModBlocks() {
        return Util.getRegistryFields(ModBlocks.class, ItemBlock.class);
    }

    public static void putTransparentBlocksToRenderLayerMap() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BREAKABLE_ICE.getBlock(), RenderLayer.getTranslucent());
    }
}
