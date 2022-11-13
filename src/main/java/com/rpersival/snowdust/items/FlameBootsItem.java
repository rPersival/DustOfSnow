package com.rpersival.snowdust.items;

import com.rpersival.snowdust.enchantment.ModEnchantments;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class FlameBootsItem extends ArmorItem {
    public FlameBootsItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos positionClicked = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        if (player == null)
            return super.useOnBlock(context);
        Enchantment enchantment = ModEnchantments.FIERY_REJECTION.getLeft();
        Block clickedBlock = context.getWorld().getBlockState(positionClicked).getBlock();
        ItemStack currentItem = player.getMainHandStack();

        boolean isCompatible = enchantment.isAcceptableItem(currentItem) &&
                EnchantmentHelper.isCompatible(EnchantmentHelper.get(currentItem).keySet(), enchantment);

        if (clickedBlock.equals(Blocks.GOLD_BLOCK) && isCompatible) {
            currentItem.addEnchantment(enchantment, 1);
            return ActionResult.CONSUME;
        }
        return super.useOnBlock(context);
    }
}
