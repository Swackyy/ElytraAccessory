package com.swacky.elytra_accessory.mixinutils.extension;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public interface ArmedEntityRenderStateExtension {
    @Nullable NonNullList<ItemStack> elytraAccessory$getItems();

    void elytraAccessory$setItems(NonNullList<ItemStack> stacks);
}
