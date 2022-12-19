package com.rpersival.snowdust;

import com.rpersival.snowdust.blocks.ModBlocks;
import com.rpersival.snowdust.entitys.client.ScarabRender;
import com.rpersival.snowdust.util.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class DustOfSnowClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModBlocks.putTransparentBlocksToRenderLayerMap();

        EntityRendererRegistry.register(ModEntities.SCARAB,ScarabRender::new);
    }
}
