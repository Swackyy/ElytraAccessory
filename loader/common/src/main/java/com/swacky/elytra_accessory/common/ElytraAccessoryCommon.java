package com.swacky.elytra_accessory.common;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.ArrayList;

public class ElytraAccessoryCommon {
    public static final String MODID = "elytra_accessory";
    public static final Identifier PACK_ID = id("elytra_type");
    public static final ArrayList<Item> BOUND_ITEMS = new ArrayList<>();

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(ElytraAccessoryCommon.MODID, path);
    }
}
