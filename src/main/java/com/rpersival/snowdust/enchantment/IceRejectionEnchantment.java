package com.rpersival.snowdust.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class IceRejectionEnchantment extends Enchantment{
    public IceRejectionEnchantment(){
        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR, new EquipmentSlot[] {EquipmentSlot.HEAD, EquipmentSlot.CHEST,
            EquipmentSlot.LEGS, EquipmentSlot.FEET});
    }

    @Override
    public int getMinPower(int level){
        return 5 + 20 * (level - 1);
    }

    @Override
    public int getMaxLevel(){
        return 1;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level)
    {
        if(attacker instanceof LivingEntity)
        {
            ((LivingEntity)attacker).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,
                    60, 2));
            ((LivingEntity)attacker).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,
                    60, 2));
        }
        super.onUserDamaged(user, attacker, level);
    }
}
