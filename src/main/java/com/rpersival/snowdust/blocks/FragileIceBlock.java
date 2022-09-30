package com.rpersival.snowdust.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FragileIceBlock extends IceBlock {

    protected boolean doMelt;

    public FragileIceBlock(Settings settings, boolean doMelt) {
        super(settings);
        this.doMelt = doMelt;
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

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state,
                           @Nullable BlockEntity blockEntity, ItemStack stack) {
        if (doMelt) {
            super.afterBreak(world, player, pos, state, blockEntity, stack);
            return;
        }
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005f);
        Block.dropStacks(state, world, pos, blockEntity, player, stack);
    }

    private void breakIce(World world, BlockState currentState, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) ||
                entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative()) return;
        MinecraftServer server = entity.getServer();

        if (server != null) {
            ServerWorld worldServer = server.getWorld(world.getRegistryKey());
            if (worldServer != null)
                worldServer.breakBlock(pos, false, entity);
            this.breakAdjacentBlocks(worldServer, pos, currentState, entity);
        }
    }

    @Override
    protected void melt(BlockState state, World world, BlockPos pos) {
        if (doMelt)
            super.melt(state, world, pos);
    }

    public void breakAdjacentBlocks(ServerWorld world, BlockPos pos, BlockState state, Entity entity) {

        double decimalX = entity.prevX - (int) entity.prevX;
        double decimalZ = entity.prevZ - (int) entity.prevZ;

        int xOffset = decimalX >= 0 ? (decimalX > 0.7 ? 1 : decimalX < 0.3 ? -1 : 0) :
                (decimalX < -0.7 ? -1 : decimalX > -0.3 ? 1 : 0);

        int zOffset = decimalZ >= 0 ? (decimalZ > 0.7 ? 1 : decimalZ < 0.3 ? -1 : 0) :
                (decimalZ < -0.7 ? -1 : decimalZ > -0.3 ? 1 : 0);

        BlockPos[] blockPos = { pos.south(zOffset), pos.east(xOffset), pos.east(xOffset).south(zOffset) };

        for (BlockPos currentPos : blockPos)
            if (!world.isAir(currentPos)
                    && world.getBlockState(currentPos).equals(state)) {
                world.breakBlock(currentPos, false);
            }
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

}
