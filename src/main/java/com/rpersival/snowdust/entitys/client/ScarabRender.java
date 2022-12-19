package com.rpersival.snowdust.entitys.client;

import com.rpersival.snowdust.DustOfSnow;
import com.rpersival.snowdust.entitys.custom.ScarabEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ScarabRender extends GeoEntityRenderer<ScarabEntity> {
    public ScarabRender(EntityRendererFactory.Context renderManager) {
        super(renderManager, new ScarabModel());
    }

    @Override
    public Identifier getTextureLocation(ScarabEntity instance) {
        return new Identifier(DustOfSnow.MOD_ID, "textures/entity/scarab.png");
    }
}
