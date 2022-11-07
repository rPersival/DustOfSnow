package com.rpersival.snowdust.mixin;

import net.minecraft.client.render.CameraSubmersionType;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(CameraSubmersionType.class)
@Unique
public abstract class CameraSubmersionTypeMixin {
    @Shadow
    @Final
    @Mutable
    private static CameraSubmersionType[] field_27889;

    private static final CameraSubmersionType QUICK_SAND = cameraSubmersionTypeExpansion$addVariant("QUICK_SAND");

    @Invoker("<init>")
    private static CameraSubmersionType cameraSubmersionTypeExpansion$invokeInit(String internalName, int internalId) {
        throw new AssertionError();
    }

    private static CameraSubmersionType cameraSubmersionTypeExpansion$addVariant(String internalName) {
        if (CameraSubmersionTypeMixin.field_27889 == null) {
            throw new NullPointerException("$VALUES cannot be null");
        }
        ArrayList<CameraSubmersionType> variants = new ArrayList<>(Arrays.asList(CameraSubmersionTypeMixin.field_27889));
        CameraSubmersionType submersionType = cameraSubmersionTypeExpansion$invokeInit(internalName,
                variants.get(variants.size() - 1).ordinal() + 1);
        variants.add(submersionType);
        CameraSubmersionTypeMixin.field_27889 = variants.toArray(new CameraSubmersionType[0]);
        return submersionType;
    }
}
