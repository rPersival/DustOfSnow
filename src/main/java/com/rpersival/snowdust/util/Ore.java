package com.rpersival.snowdust.util;

import com.rpersival.snowdust.DustOfSnow;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Ore {
    private final Identifier id;
    private final int frequency;
    private final int minHeight;
    private final int maxHeight;
    private final OreFeatureConfig config;
    private final ConfiguredFeature<? extends FeatureConfig, ?> feature;
    private final PlacedFeature placedFeature;
    private boolean isRegistered;

    public Ore(String name, int frequency, int minHeight, int maxHeight, OreFeatureConfig config) {
        this.id = new Identifier(DustOfSnow.MOD_ID, name);
        this.frequency = frequency;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;

        this.config = config;

        this.feature = new ConfiguredFeature<>(Feature.ORE, config);

        this.placedFeature = new PlacedFeature(
                RegistryEntry.of(feature),
                Arrays.asList(
                        CountPlacementModifier.of(frequency),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.fixed(minHeight), YOffset.fixed(maxHeight))
                )
        );
        this.isRegistered = false;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public Identifier getId() {
        return id;
    }

    public OreFeatureConfig getConfig() {
        return config;
    }

    public ConfiguredFeature<? extends FeatureConfig, ?> getFeature() {
        return feature;
    }

    public int getVeinSize() {
        return config.size;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public ConfiguredFeature<? extends FeatureConfig, ?> getConfiguredFeature() {
        return this.feature;
    }

    public PlacedFeature getPlacedFeature() {
        return this.placedFeature;
    }

    public void register() {
        if (!isRegistered) {
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, feature);
            Registry.register(BuiltinRegistries.PLACED_FEATURE, id, placedFeature);
            isRegistered = true;
        }
    }

    public void addFeatures(Predicate<BiomeSelectionContext> biomeSelectors) {
        BiomeModifications.addFeature(biomeSelectors, GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, id));
    }

    public void addFeatures(List<RegistryKey<Biome>> allowedBiomes) {
        addFeatures(BiomeSelectors.includeByKey(allowedBiomes));
    }
}
