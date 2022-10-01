package com.rpersival.snowdust.world.gen.ore;

import com.rpersival.snowdust.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres(){
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                BiomeKeys.DEEP_FROZEN_OCEAN,
                BiomeKeys.FROZEN_OCEAN,
                BiomeKeys.FROZEN_PEAKS,
                BiomeKeys.FROZEN_RIVER,
                BiomeKeys.ICE_SPIKES,
                BiomeKeys.SNOWY_BEACH,
                BiomeKeys.SNOWY_PLAINS,
                BiomeKeys.SNOWY_TAIGA,
                BiomeKeys.SNOWY_SLOPES), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.CRAUNIUM_ORE_PLACED.getKey().get());
    }
}
