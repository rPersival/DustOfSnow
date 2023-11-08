package com.rpersival.snowdust.blocks;

import com.rpersival.snowdust.DustOfSnow;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import javax.annotation.Nullable;
import java.util.*;

public class FragileIceBlock extends IceBlock {
    //TODO: add fragility field
    private final int maxPropagation;
    protected final boolean doMelt;
    private final Random random;

    public FragileIceBlock(Settings settings, boolean doMelt, int maxPropagation) {
        super(settings);
        this.doMelt = doMelt;
        this.maxPropagation = maxPropagation;
        random = new Random();
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance);
        DustOfSnow.LOGGER.warn("onLandedUpon fired!");
        this.breakIce(world, state, pos, entity, fallDistance);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
         if (entity.fallDistance != 0.0f) {
            return;
        }
        DustOfSnow.LOGGER.warn("onSteppedOn fired!");
        this.breakIce(world, state, pos, entity, 0.0f);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
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

    private void breakIce(World world, BlockState currentState, BlockPos pos, Entity entity, float fallDistance) {
        if (!(entity instanceof LivingEntity) ||
                entity instanceof PlayerEntity playerEntity && playerEntity.isCreative()) {
            return;
        }
        MinecraftServer server = entity.getServer();

        if (server != null) {
            ServerWorld worldServer = server.getWorld(world.getRegistryKey());
            if (worldServer != null) {
                this.breakAdjacentBlocks(worldServer, pos, currentState, entity, fallDistance);
            }
        }
    }

    @Override
    protected void melt(BlockState state, World world, BlockPos pos) {
        if (doMelt) {
            super.melt(state, world, pos);
        }
    }

    public void breakAdjacentBlocks(ServerWorld world, BlockPos pos, BlockState state, Entity entity,
                                    float fallDistance) {
        for (BlockPos currentPos : getPropagatedSquares(entity.getX(), entity.getZ(), pos.getY(),
                (int) Math.ceil(calculatePropagation(fallDistance, entity.getVelocity(), entity.getWidth())),
                state, world)) {
            if (!world.isAir(currentPos) && world.getBlockState(currentPos).equals(state)) {
                world.breakBlock(currentPos, false);
            }
        }
    }

    public List<BlockPos> getPropagatedSquares(double x, double z, double y, int propagation,
                                               BlockState state, ServerWorld world) {
        List<BlockPos> propagatedSquares = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();

        BlockPos centerSquare = new BlockPos(x, y, z);

        int[] dx = {0, 1, 0, -1};
        int[] dz = {1, 0, -1, 0};

        if (!world.isAir(centerSquare)) {
            queue.offer(centerSquare);
            visited.add(centerSquare);
        }

        double probability = 1.0;

        for (int distance = 0; !queue.isEmpty() && distance <= propagation; distance++) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                BlockPos current = queue.poll();
                propagatedSquares.add(current);

                assert current != null;

                for (int j = 0; j < 4; j++) {
                    int nextX = current.getX() + dx[j];
                    int nextZ = current.getZ() + dz[j];
                    BlockPos nextSquare = new BlockPos(nextX, y, nextZ);

                    if (!world.isAir(nextSquare) && !visited.contains(nextSquare)
                            && world.getBlockState(nextSquare).equals(state) && random.nextDouble() < probability) {
                        queue.offer(nextSquare);
                        visited.add(nextSquare);
                    }
                }
            }

            probability = getProbability(distance, propagation);
        }

        return propagatedSquares;
    }

    private double getProbability(int iteration, int propagation) {
        if (propagation <= 0) return 0;
        double x = (iteration + random.nextDouble(1.0 / propagation)) / propagation;
        if (x <= 0.5) {
            return 1.0 - 0.4 * x;
        } else {
            return 0.8 / x - 0.8;
        }
    }

    private double calculatePropagation(Float fallDistance, Vec3d velocity, float length) {
        double min = length + 2;

        if (maxPropagation <= min) {
            return maxPropagation;
        }

        double fallFunction;

        if (fallDistance < 1.0f) {
            fallFunction = 0.0f;
        } else {
            fallFunction = 2.3 * Math.pow(fallDistance, 1 / 1.87) +
                    1.2 * Math.pow(fallDistance, 1 / 3.7) - 3.6876;
        }
        double cumulativeVelocity = velocity.length();
        double propagation = 20.0d * Math.pow((cumulativeVelocity), 0.55d) + fallFunction;
        return Math.min(Math.max(propagation, min), maxPropagation);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }
}