package com.rpersival.snowdust.util;

import net.minecraft.entity.damage.DamageSource;

public class ModDamageSource extends DamageSource {

    public static final DamageSource QUICK_SAND_SUFFOCATION =
            (new ModDamageSource("quickSandSuffocation")).setBypassesArmor();

    public ModDamageSource(String name) {
        super(name);
    }
}
