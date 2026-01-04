package com.swacky.elytra_accessory.mixinutils;

import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public record AccessoryRenderStateData(NonNullList<ItemStack> items) {
        public static final RenderStateDataKey<AccessoryRenderStateData> KEY = RenderStateDataKey.create(() -> "accessoryItems");
}