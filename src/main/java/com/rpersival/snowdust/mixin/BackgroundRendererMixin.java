package com.rpersival.snowdust.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {

    @Inject(at = @At("TAIL"), method = "render", cancellable = true)
    private static void render(Camera camera, float tickDelta, ClientWorld world, int i, float f, CallbackInfo ci) {
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();

        if (!cameraSubmersionType.equals(CameraSubmersionType.valueOf("QUICK_SAND"))) {
            return;
        }

        Entity entity = camera.getFocusedEntity();

        float red = 0.796f;
        float green = 0.67f;
        float blue = 0.5f;

        float z = (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.NIGHT_VISION) ?
                GameRenderer.getNightVisionStrength((LivingEntity)entity, tickDelta) : 0.0f);

        if (f > 0.0f) {
            red = red * (1.0f - f) + red * 0.7f * f;
            green = green * (1.0f - f) + green * 0.6f * f;
            blue = blue * (1.0f - f) + blue * 0.6f * f;
        }
        float u = Math.min(1.0f / red, Math.min(1.0f / green, 1.0f / blue));

        red = red * (1.0f - z) + red * u * z;
        green = green * (1.0f - z) + green * u * z;
        blue = blue * (1.0f - z) + blue * u * z;

        BackgroundRendererAccessor.setRed(red);
        BackgroundRendererAccessor.setGreen(green);
        BackgroundRendererAccessor.setBlue(blue);
        RenderSystem.clearColor(red, green, blue, 0.0f);

        ci.cancel();
    }

    @Inject(at = @At("TAIL"), method = "applyFog")
    private static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog,
                                 CallbackInfo ci) {
        float f;
        float g;
        Entity entity = camera.getFocusedEntity();
        if (camera.getSubmersionType().equals(CameraSubmersionType.valueOf("QUICK_SAND"))) {
            if (entity.isSpectator()) {
                f = -8.0f;
                g = viewDistance * 0.5f;
            } else {
                f = 0.0f;
                g = 2.0f;
            }
        } else {
            return;
        }
        RenderSystem.setShaderFogStart(f);
        RenderSystem.setShaderFogEnd(g);
    }
}
