package com.swacky.elytra_accessory.event;

import com.swacky.ohmega.client.renderer.AccessoryRenderStateData;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public final class ClientCallbacks {
    public static boolean shouldPreventCapeRender(AvatarRenderState state) {
        AccessoryRenderStateData data = AccessoryRenderStateData.getData(state);

        if (data != null) {
            for (ItemStack stack : data.stacks()){
                if (LivingEntity.canGlideUsing(stack, EquipmentSlot.CHEST)) {
                    return true;
                }
            }
        }

        return false;
    }
}
