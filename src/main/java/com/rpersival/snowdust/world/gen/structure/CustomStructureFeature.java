package com.rpersival.snowdust.world.gen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Optional;

public class CustomStructureFeature extends StructureFeature<StructurePoolFeatureConfig> {

    public static final Codec<StructurePoolFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            StructurePool.REGISTRY_CODEC.fieldOf("start_pool")
                                    .forGetter(StructurePoolFeatureConfig::getStartPool),
                            Codec.intRange(0, 100).fieldOf("size").forGetter(StructurePoolFeatureConfig::getSize)
                    )
                    .apply(instance, StructurePoolFeatureConfig::new)
    );

    public CustomStructureFeature(int height) {
        super(CODEC, (context) -> createPiecesGenerator(context, height),
                PostPlacementProcessor.EMPTY);
    }

    private static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        return true;
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(
            StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context, int height) {

        if (!com.rpersival.snowdust.world.gen.structure.CustomStructureFeature.isFeatureChunk(context))
            return Optional.empty();

        BlockPos blockpos = context.chunkPos().getCenterAtY(0);

        blockpos = blockpos.up(1 + height);

        return StructurePoolBasedGenerator.generate(context, PoolStructurePiece::new, blockpos, false, true);
    }
}
