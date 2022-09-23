package com.rpersival.snowdust.mixin;

import com.rpersival.snowdust.DustOfSnow;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class DustOfSnowMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		DustOfSnow.LOGGER.info("This line is printed by the Dust of Snow mixin!");
	}
}
