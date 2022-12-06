package com.rpersival.snowdust.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class TrueIceArmorMaterial implements ArmorMaterial {
    public static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
    public static final int[] PROTECTION_VALUES = new int[] {2, 6, 8, 2};

    @Override
    public int getDurability(EquipmentSlot slot){
        return BASE_DURABILITY[slot.getEntitySlotId()] * 100;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot){
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability(){
        return 20;
    }

    @Override
    public SoundEvent getEquipSound(){
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairIngredient(){
        return Ingredient.ofItems(ModItems.CRAUNIUM_INGOT.getLeft());
    }

    @Override
    public String getName(){
        return "true_ice";
    }

    @Override
    public float getToughness(){
        return 0.0f;
    }

    @Override
    public float getKnockbackResistance(){
        return 0.0f;
    }
}
