package com.rpersival.snowdust.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IceAxeItem extends SwordItem {

    public IceAxeItem(int attackDamage, float attackSpeed, ToolMaterial material, Item.Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        Material material = state.getMaterial();
        boolean isIceMaterial = material.equals(Material.ICE) || material.equals(Material.DENSE_ICE);
        if (state.getHardness(world, pos) != 0.0f)
            stack.damage(isIceMaterial ? 1 : 2, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        Material material = state.getMaterial();

        return material.equals(Material.ICE) || material.equals(Material.DENSE_ICE);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();

        if (material.equals(Material.ICE) || material.equals(Material.DENSE_ICE))
            return 15.0f;

        return 1.0f;
    }
}
