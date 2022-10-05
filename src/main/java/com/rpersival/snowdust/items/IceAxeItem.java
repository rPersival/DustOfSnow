package com.rpersival.snowdust.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;

public class IceAxeItem extends PickaxeItem {

    public IceAxeItem(int attackDamage, float attackSpeed, ToolMaterial material, Item.Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        Material material = state.getMaterial();

        return material.equals(Material.ICE) || material.equals(Material.DENSE_ICE) || super.isSuitableFor(state);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean flag = super.postHit(stack, target, attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 5, 1));
        return flag;
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        float miningSpeed = super.getMiningSpeedMultiplier(stack, state);
        Material material = state.getMaterial();

        if (material.equals(Material.ICE) || material.equals(Material.DENSE_ICE))
            return miningSpeed * 15.0f;

        return miningSpeed;
    }
}
