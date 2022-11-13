package com.rpersival.snowdust.mixin;

import net.minecraft.client.render.BackgroundRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BackgroundRenderer.class)
public interface BackgroundRendererAccessor {

    @Accessor("red")
    static void setRed(float red) {
        throw new AssertionError();
    }

    @Accessor("blue")
    static void setBlue(float blue) {
        throw new AssertionError();
    }

    @Accessor("green")
    static void setGreen(float green) {
        throw new AssertionError();
    }
}
