package com.rpersival.snowdust.enchantment;

import com.rpersival.snowdust.util.Register;
import com.rpersival.snowdust.util.Util;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Pair;

import java.util.List;


public class ModEnchantments {

    @Register
    public static Pair<Enchantment, String> ICE_REJECTION =
            new Pair<>(new IceRejectionEnchantment(), "ice_rejection");

    @SuppressWarnings("unchecked")
    public static List<Pair<Enchantment, String>> getEnchantments() {
        return (List<Pair<Enchantment, String>>)
                Util.getRegistryFields(ModEnchantments.class, ICE_REJECTION.getClass());
    }

}
