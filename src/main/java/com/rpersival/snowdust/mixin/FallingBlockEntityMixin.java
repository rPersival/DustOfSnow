package com.rpersival.snowdust.mixin;

import com.rpersival.snowdust.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin {

    @Inject(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/FallingBlockEntity;" +
                    "dropItem(Lnet/minecraft/item/ItemConvertible;)" +
                    "Lnet/minecraft/entity/ItemEntity;"),
            method = "tick",
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true)
    public void tick(CallbackInfo ci, Block block) {
        FallingBlockEntity thisFallingBlockEntity = (FallingBlockEntity) (Object) this;
        if (block.equals(ModBlocks.QUICK_SAND.getBlock())) {
            thisFallingBlockEntity.dropItem(Blocks.SAND);
            ci.cancel();
        }
    }
}