package com.rpersival.snowdust.mixin;

import com.rpersival.snowdust.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(at = @At("RETURN"), method = "getBlockBreakingSpeed", locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true)
    public void getBlockBreakingSpeed(BlockState block, CallbackInfoReturnable<Float> cir, float f) {
        PlayerEntity thisPlayer = (PlayerEntity) (Object) this;
        if (thisPlayer.getBlockStateAtPos().isOf(ModBlocks.QUICK_SAND.getBlock()))
            f /= 10.0f;
        cir.setReturnValue(f);
    }
}
