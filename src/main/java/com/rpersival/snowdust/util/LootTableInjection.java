package com.rpersival.snowdust.util;

import com.rpersival.snowdust.items.ModItems;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

public class LootTableInjection {

    public static final Identifier ICE_BLOCK_IDENTIFIER =
            new Identifier("minecraft", "blocks/ice");

    public static final Identifier BLUE_ICE_BLOCK_IDENTIFIER =
            new Identifier("minecraft", "blocks/blue_ice");

    public static final Identifier PACKED_ICE_BLOCK_IDENTIFIER =
            new Identifier("minecraft", "blocks/packed_ice");

    public static final Identifier DENSE_ICE_BLOCK_IDENTIFIER =
            new Identifier("minecraft", "blocks/frosted_ice");

    public static void injectIntoVanillaLootTable() {
        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {

            if (ICE_BLOCK_IDENTIFIER.equals(id))
                supplier.withPool(getPoolThatDropItemWithMatchingTool(Items.ICE, ModItems.ICE_AXE.getLeft()).build());

            else if (BLUE_ICE_BLOCK_IDENTIFIER.equals(id))
                supplier.withPool(getPoolThatDropItemWithMatchingTool(Items.BLUE_ICE, ModItems.ICE_AXE.getLeft()).build());

            else if (PACKED_ICE_BLOCK_IDENTIFIER.equals(id))
                supplier.withPool(getPoolThatDropItemWithMatchingTool(Items.PACKED_ICE, ModItems.ICE_AXE.getLeft()).build());

            else if (DENSE_ICE_BLOCK_IDENTIFIER.equals(id))
                supplier.withPool(getPoolThatDropItemWithMatchingTool(Blocks.FROSTED_ICE, ModItems.ICE_AXE.getLeft()).build());

        }));
    }

    private static FabricLootPoolBuilder getPoolThatDropItemWithMatchingTool(ItemConvertible dropItem, Item tool) {
        return FabricLootPoolBuilder.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(dropItem))
                .conditionally(MatchToolLootCondition.builder(
                        ItemPredicate.Builder.create().items(tool)));
    }
}
