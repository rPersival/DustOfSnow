package com.rpersival.snowdust.util;

import com.rpersival.snowdust.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModOres {

    public static final Ore CRAUNIUM_ORE = new Ore("craunium_ore", 4, -80, 80,
            getOverworldOreFeatureConfig(ModBlocks.CRAUNIUM_ORE.getBlock(),
                    ModBlocks.DEEPSLATE_CRAUNIUM_ORE.getBlock(), 4));

    public static void generateOres() {
        ModRegistry.registerOres();
        addCrauniumFeatures();
    }

    public static void addCrauniumFeatures() {
        List<RegistryKey<Biome>> allowedBiomes = Arrays.asList(
                BiomeKeys.DEEP_FROZEN_OCEAN,
                BiomeKeys.FROZEN_OCEAN,
                BiomeKeys.FROZEN_PEAKS,
                BiomeKeys.FROZEN_RIVER,
                BiomeKeys.ICE_SPIKES,
                BiomeKeys.SNOWY_BEACH,
                BiomeKeys.SNOWY_PLAINS,
                BiomeKeys.SNOWY_TAIGA,
                BiomeKeys.SNOWY_SLOPES
        );

        if (CRAUNIUM_ORE.isRegistered())
            CRAUNIUM_ORE.addFeatures(allowedBiomes);
    }

    public static List<Ore> getModItems() {
        return Arrays.stream(ModOres.class.getDeclaredFields())
                .filter(field -> Modifier.isStatic(field.getModifiers()))
                .map(field -> {
                    try {
                        return (Ore) field.get(ModOres.class);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    private static OreFeatureConfig getOverworldOreFeatureConfig(Block overworldBlock, Block deepSlateBlock, int size) {
        List<OreFeatureConfig.Target> targets = List.of(
                OreFeatureConfig.createTarget(
                        OreConfiguredFeatures.STONE_ORE_REPLACEABLES, overworldBlock.getDefaultState()),
                OreFeatureConfig.createTarget(
                        OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, deepSlateBlock.getDefaultState()));

        return new OreFeatureConfig(targets, size);
    }

    private static OreFeatureConfig getNetherOreFeatureConfig(Block block, int size) {
        return new OreFeatureConfig(OreConfiguredFeatures.NETHERRACK, block.getDefaultState(), size);
    }

    private static OreFeatureConfig getEndOreFeatureConfig(Block block, int size) {
        return new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE), block.getDefaultState(), size);
    }
}
