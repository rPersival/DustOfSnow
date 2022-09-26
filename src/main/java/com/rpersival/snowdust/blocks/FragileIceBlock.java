package com.rpersival.snowdust.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.IceBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FragileIceBlock extends IceBlock {

    public FragileIceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance);
        this.breakIce(world, state, pos, entity);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        this.breakIce(world, state, pos, entity);
    }

    private void breakIce(World world, BlockState currentState, BlockPos pos, Entity entity) {
        if (entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative()) return;
        MinecraftServer server = entity.getServer();

        if (server != null) {
            ServerWorld worldServer = server.getWorld(world.getRegistryKey());
            if (worldServer != null)
                worldServer.breakBlock(pos, false, entity);
            this.breakAdjacentBlocks(worldServer, pos, currentState, entity);
        }
    }

    public void breakAdjacentBlocks(ServerWorld world, BlockPos pos, BlockState state, Entity entity) {

        double decimalX = entity.prevX - (int) entity.prevX;
        double decimalZ = entity.prevZ - (int) entity.prevZ;

        int xOffset = decimalX > 0.65 ? 1 : decimalX < 0.35 ? -1 : 0;
        int zOffset = decimalZ > 0.65 ? 1 : decimalZ < 0.35 ? -1 : 0;

        BlockPos[] blockPos = { pos.south(zOffset), pos.east(xOffset), pos.east(xOffset).south(zOffset) };

        for (BlockPos currentPos : blockPos)
            if (!world.isAir(currentPos)
                    && world.getBlockState(currentPos).equals(state)) {
                world.breakBlock(currentPos, false);
            }

    }
}
