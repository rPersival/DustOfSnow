package com.rpersival.snowdust.items;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class SoulSickleItem extends SwordItem {
    public SoulSickleItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.isDead()) {
            World world = target.getWorld();
            world.spawnEntity(new ItemEntity(world, target.getX(), target.getY(), target.getZ(),
                    new ItemStack(ModItems.SEALED_SOULS.getLeft(), 1)));
        }
        else
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60,1));

        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

}
