package com.rpersival.snowdust.blocks;

import com.rpersival.snowdust.util.ModDamageSource;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;

public class QuickSandBlock extends FallingBlock {
    private final int color;
    private final BlockState hardenedState;

    public QuickSandBlock(int color, Settings settings, Block hardened) {
        super(settings);
        this.color = color;
        this.hardenedState = hardened.getDefaultState();
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            return true;
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity.getBlockStateAtPos().isOf(this)) {
            entity.slowMovement(state, new Vec3d(0.5f, 0.15f, 0.5f));
            if (entity instanceof LivingEntity livingEntity && isEntitySubmerged(livingEntity, pos)) {
                if (livingEntity.hasVehicle() && livingEntity.getVehicle() != null) {
                    livingEntity.stopRiding();
                }
                if (livingEntity.isAlive()) {
                    suffocate(livingEntity);
                }
            }
        }
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if ((double) fallDistance < 4.0 || !(entity instanceof LivingEntity livingEntity)) {
            return;
        }
        LivingEntity.FallSounds fallSounds = livingEntity.getFallSounds();
        SoundEvent soundEvent = (double) fallDistance < 7.0 ? fallSounds.small() : fallSounds.big();
        entity.playSound(soundEvent, 1.0f, 1.0f);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Entity entity;
        if (context instanceof EntityShapeContext && (entity = ((EntityShapeContext) context).getEntity()) != null) {
            if (entity instanceof FallingBlockEntity) {
                return super.getCollisionShape(state, world, pos, context);
            }
        }
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return true;
    }

    @Override
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return this.color;
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos,
                          FallingBlockEntity fallingBlockEntity) {
        if (QuickSandBlock.shouldHarden(world, pos, currentStateInPos))
            world.setBlockState(pos, this.hardenedState, Block.NOTIFY_ALL);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos;
        World blockView = ctx.getWorld();

        if (QuickSandBlock.shouldHarden(blockView, blockPos = ctx.getBlockPos(), blockView.getBlockState(blockPos)))
            return this.hardenedState;

        return super.getPlacementState(ctx);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                WorldAccess world, BlockPos pos, BlockPos neighborPos) {

        if (QuickSandBlock.hardensOnAnySide(world, pos))
            return this.hardenedState;

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private static boolean shouldHarden(BlockView world, BlockPos pos, BlockState state) {
        return QuickSandBlock.hardensIn(state) || QuickSandBlock.hardensOnAnySide(world, pos);
    }

    private static boolean hardensOnAnySide(BlockView world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        for (Direction direction : Direction.values()) {
            BlockState blockState = world.getBlockState(mutable);
            if (direction == Direction.DOWN && !QuickSandBlock.hardensIn(blockState)) continue;
            mutable.set(pos, direction);
            blockState = world.getBlockState(mutable);
            if (QuickSandBlock.hardensIn(blockState)
                    && !blockState.isSideSolidFullSquare(world, pos, direction.getOpposite()))
                return true;
        }
        return false;
    }

    private static boolean hardensIn(BlockState state) {
        return state.getFluidState().isIn(FluidTags.WATER);
    }

    private static boolean isEntitySubmerged(Entity entity, BlockPos pos) {
        double d = entity.getEyeY() - 1.1111111119389534;
        double e = pos.getY();
        return e > d;
    }

    private static void suffocate(LivingEntity entity) {
        entity.damage(ModDamageSource.QUICK_SAND_SUFFOCATION, 1.0f);
    }
}
