package com.rpersival.snowdust.items;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class TrueIceToolMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 1200;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 10.0f;
    }

    @Override
    public float getAttackDamage() {
        return 0f;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.CRAUNIUM_INGOT.getLeft());
    }
}
