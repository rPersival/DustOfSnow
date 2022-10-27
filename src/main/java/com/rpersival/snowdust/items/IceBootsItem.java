package com.rpersival.snowdust.items;

import com.rpersival.snowdust.enchantment.ModEnchantments;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class IceBootsItem extends ArmorItem {
    public IceBootsItem(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Item.Settings settings) {
        super(armorMaterial, equipmentSlot, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos positionClicked = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        if (player == null)
            return super.useOnBlock(context);
        Enchantment enchantment = ModEnchantments.ICE_REJECTION.getLeft();
        Block clickedBlock = context.getWorld().getBlockState(positionClicked).getBlock();
        ItemStack currentItem = player.getMainHandStack();

        boolean isCompatible = enchantment.isAcceptableItem(currentItem) &&
                EnchantmentHelper.isCompatible(EnchantmentHelper.get(currentItem).keySet(), enchantment);

        if (clickedBlock.equals(Blocks.DIAMOND_BLOCK) && isCompatible) {
            currentItem.addEnchantment(enchantment, 1);
            return ActionResult.CONSUME;
        }
        return super.useOnBlock(context);
    }
}
