package com.rpersival.snowdust.blocks;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

public class QuickSandBlock extends FallingBlock{
    private final int color;

    public QuickSandBlock(int color, Settings settings) {
        super(settings);
        this.color = color;
    }

    @Override
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return this.color;
    }
}
