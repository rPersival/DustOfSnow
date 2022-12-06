package com.rpersival.snowdust.items;

import net.minecraft.block.Block;
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

public class AwokenArmorItem extends ArmorItem {

    private final Enchantment enchantment;
    private final Block block;

    public AwokenArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings, Enchantment enchantment,
                           Block block) {
        super(material, slot, settings);
        this.enchantment = enchantment;
        this.block = block;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos positionClicked = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        if (player == null)
            return super.useOnBlock(context);
        Block clickedBlock = context.getWorld().getBlockState(positionClicked).getBlock();
        ItemStack currentItem = player.getMainHandStack();

        boolean isCompatible = enchantment.isAcceptableItem(currentItem) &&
                EnchantmentHelper.isCompatible(EnchantmentHelper.get(currentItem).keySet(), enchantment);

        if (clickedBlock.equals(block) && isCompatible) {
            currentItem.addEnchantment(enchantment, 1);
            return ActionResult.CONSUME;
        }
        return super.useOnBlock(context);
    }
}
