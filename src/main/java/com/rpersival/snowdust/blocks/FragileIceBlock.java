package com.rpersival.snowdust.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.IceBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class FragileIceBlock extends IceBlock {

    public FragileIceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance);
        world.breakBlock(pos, false, entity);
        this.breakAdjacentBlocks(world, pos, state);
    }

    public void breakAdjacentBlocks(WorldAccess world, BlockPos pos, BlockState state) {
        BlockPos[] blockPos = { pos.east(1), pos.west(1), pos.north(1), pos.south(1) };

        for (BlockPos currentPos : blockPos)
            if (!world.isAir(currentPos)
                    && world.getBlockState(currentPos).equals(state)) {
                world.breakBlock(currentPos, false);
            }

    }
}
