package com.rpersival.snowdust.mixin;

import com.rpersival.snowdust.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;

@Mixin(Camera.class)
public abstract class CameraMixin {

    @Inject(method = "getSubmersionType()Lnet/minecraft/client/render/CameraSubmersionType;",
            at = @At(value = "INVOKE", target = "net/minecraft/world/BlockView.getBlockState" +
                            " (Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true)
    private void getSubmersionType(CallbackInfoReturnable<CameraSubmersionType> cir,
                                   FluidState fluidState, Camera.Projection projection,
                                   List<Vec3d> list, Iterator<Vec3d> var4, Vec3d vec3d, Vec3d vec3d2,
                                   BlockPos blockPos) {
        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
        BlockView area = ((CameraAccessor) camera).getArea();
        BlockState blockState = area.getBlockState(blockPos);
        if (blockState.isOf(ModBlocks.QUICK_SAND.getBlock())) {
            cir.setReturnValue(CameraSubmersionType.valueOf("QUICK_SAND"));
        }
    }
}
