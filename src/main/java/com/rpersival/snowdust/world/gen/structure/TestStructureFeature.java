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

public class TestStructureFeature extends StructureFeature<StructurePoolFeatureConfig> {

    public static final Codec<StructurePoolFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            StructurePool.REGISTRY_CODEC.fieldOf("start_pool")
                                    .forGetter(StructurePoolFeatureConfig::getStartPool),
                            Codec.intRange(0, 40).fieldOf("size").forGetter(StructurePoolFeatureConfig::getSize)
                    )
                    .apply(instance, StructurePoolFeatureConfig::new)
    );

    public TestStructureFeature() {
        super(CODEC, TestStructureFeature::createPiecesGenerator, PostPlacementProcessor.EMPTY);
    }

    private static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
//        ChunkPos chunkpos = context.chunkPos();
        return true;
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(
            StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {

        if (!TestStructureFeature.isFeatureChunk(context))
            return Optional.empty();

        BlockPos blockpos = context.chunkPos().getCenterAtY(0);

        blockpos = blockpos.up(1);

        return StructurePoolBasedGenerator.generate(context, PoolStructurePiece::new, blockpos, false, true);
    }
}
