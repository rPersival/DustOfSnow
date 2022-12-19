package com.rpersival.snowdust;

import com.rpersival.snowdust.util.LootTableInjection;
import com.rpersival.snowdust.util.ModOres;
import com.rpersival.snowdust.util.ModRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DustOfSnow implements ModInitializer {
	public static final String MOD_ID = "snowdust";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello from Dust of Snow!");
		registerEverything();
	}

	public static void registerEverything() {
		ModRegistry.registerItems();
		ModRegistry.registerBlocks();
		ModRegistry.registerStructureFeatures();
		ModRegistry.registerEnchantment();
		ModRegistry.registerAttributes();
		ModOres.generateOres();
		LootTableInjection.injectIntoVanillaLootTable();
	}
}
