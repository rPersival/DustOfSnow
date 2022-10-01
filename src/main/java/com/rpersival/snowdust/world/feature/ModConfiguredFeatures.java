package com.rpersival.snowdust.world.feature;

import com.rpersival.snowdust.blocks.ModBlocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {
    public static final List<OreFeatureConfig.Target> OVERWORLD_CRAUNIUM_ORES = List.of(
            OreFeatureConfig.createTarget(
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    ModBlocks.CRAUNIUM_ORE.getBlock().getDefaultState()),
            OreFeatureConfig.createTarget(
                    OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    ModBlocks.DEEPSLATE_CRAUNIUM_ORE.getBlock().getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> CRAUNIUM_ORE =
            ConfiguredFeatures.register("craunium_ore", Feature.ORE,
                    new OreFeatureConfig(OVERWORLD_CRAUNIUM_ORES, 4));
}
