package com.rpersival.snowdust;

import com.rpersival.snowdust.blocks.ModBlocks;
import net.fabricmc.api.ClientModInitializer;

public class DustOfSnowClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModBlocks.putTransparentBlocksToRenderLayerMap();
    }
}
