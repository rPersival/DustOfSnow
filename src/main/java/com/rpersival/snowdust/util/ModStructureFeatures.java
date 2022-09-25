package com.rpersival.snowdust.util;

import com.rpersival.snowdust.world.gen.structure.TestStructureFeature;
import net.minecraft.util.Pair;
import net.minecraft.world.gen.feature.StructureFeature;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModStructureFeatures {

    public static final Pair<StructureFeature<?>, String> TEST_STRUCTURE = new Pair<>(new TestStructureFeature(),
            "test_structure_feature");

    @SuppressWarnings("unchecked")
    public static List<Pair<StructureFeature<?>, String>> getStructureFeatures() {
        return Arrays.stream(ModStructureFeatures.class.getDeclaredFields())
                .filter(field -> Modifier.isStatic(field.getModifiers()))
                .map(field -> {
                    try {
                        return (Pair<StructureFeature<?>, String>) field.get(ModStructureFeatures.class);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}
