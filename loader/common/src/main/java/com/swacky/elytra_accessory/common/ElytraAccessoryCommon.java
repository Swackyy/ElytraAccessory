package com.swacky.elytra_accessory.common;

import net.minecraft.resources.Identifier;

public class ElytraAccessoryCommon {
    public static final String MODID = "elytra_accessory";
    public static final Identifier PACK_ID = id("elytra_type");

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(ElytraAccessoryCommon.MODID, path);
    }
}
