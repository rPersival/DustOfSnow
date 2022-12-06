package com.rpersival.snowdust.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SealedSoulsItem extends Item {

    public SealedSoulsItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        ItemStack itemStack = entity.getStackInHand(hand);

        world.playSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                SoundCategory.PLAYERS, 0.5f,
                (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.35f + 0.9f, false);

        entity.addExperience((int) Math.round(Math.random() * 15 + 5));

        if (!entity.isCreative())
            itemStack.decrement(1);

        return super.use(world, entity, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

}
