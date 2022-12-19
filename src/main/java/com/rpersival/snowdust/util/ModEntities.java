package com.rpersival.snowdust.util;

import com.rpersival.snowdust.DustOfSnow;
import com.rpersival.snowdust.entitys.custom.ScarabEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    public static final EntityType<ScarabEntity> SCARAB = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(DustOfSnow.MOD_ID, "scarab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ScarabEntity::new)
                    .dimensions(EntityDimensions.fixed(0.2f, 0.2f)).build());
}
