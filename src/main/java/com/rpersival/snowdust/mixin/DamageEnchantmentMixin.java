package com.rpersival.snowdust.mixin;

import com.rpersival.snowdust.items.IceAxeItem;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageEnchantment.class)
public abstract class DamageEnchantmentMixin {
    @Inject(at = @At("HEAD"),
            method = "isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z", cancellable = true)
    public void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof IceAxeItem)
            cir.setReturnValue(true);
    }

}
