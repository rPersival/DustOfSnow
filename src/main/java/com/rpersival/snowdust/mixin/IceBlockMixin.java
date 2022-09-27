package com.rpersival.snowdust.mixin;

import com.rpersival.snowdust.items.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBlock.class)
public abstract class IceBlockMixin extends TransparentBlock {

    @Inject(at = @At("HEAD"),
            method = "afterBreak(Lnet/minecraft/world/World;" +
            "Lnet/minecraft/entity/player/PlayerEntity;" +
            "Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;" +
            "Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/item/ItemStack;)V",
        cancellable = true)
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state,
                           @Nullable BlockEntity blockEntity, ItemStack stack,
                           CallbackInfo info) {

        if (stack.getItem().equals(ModItems.ICE_AXE.getLeft())) {
            super.afterBreak(world, player, pos, state, blockEntity, stack);
            info.cancel();
        }
    }

    public IceBlockMixin(Settings settings) {
        super(settings);
    }
}
