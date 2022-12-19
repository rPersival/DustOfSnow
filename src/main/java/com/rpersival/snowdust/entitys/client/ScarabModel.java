package com.rpersival.snowdust.entitys.client;

import com.rpersival.snowdust.DustOfSnow;
import com.rpersival.snowdust.entitys.custom.ScarabEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ScarabModel extends AnimatedGeoModel<ScarabEntity> {
    @Override
    public Identifier getModelLocation(ScarabEntity object) {
        return new Identifier(DustOfSnow.MOD_ID, "geo/scarab.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ScarabEntity object) {
        return new Identifier(DustOfSnow.MOD_ID, "textures/entity/scarab.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ScarabEntity animatable) {
        return new Identifier(DustOfSnow.MOD_ID, "animations/scarab.animation.json");
    }
}
