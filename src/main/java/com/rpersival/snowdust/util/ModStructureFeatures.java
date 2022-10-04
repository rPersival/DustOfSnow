package com.rpersival.snowdust.util;

import com.rpersival.snowdust.world.gen.structure.CustomStructureFeature;
import com.rpersival.snowdust.world.gen.structure.TestStructureFeature;
import net.minecraft.util.Pair;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;

public class ModStructureFeatures {

    @Register
    public static final Pair<StructureFeature<?>, String> TEST_STRUCTURE = new Pair<>(new TestStructureFeature(),
            "test_structure_feature");

    @Register
    public static final Pair<StructureFeature<?>, String> GRAVEYARD_FEATURE = new Pair<>(
            new CustomStructureFeature(-8),
            "graveyard_dungeon_feature");

    @SuppressWarnings("unchecked")
    public static List<Pair<StructureFeature<?>, String>> getStructureFeatures() {
        return (List<Pair<StructureFeature<?>, String>>)
                Util.getRegistryFields(ModStructureFeatures.class, TEST_STRUCTURE.getClass());
    }
}
