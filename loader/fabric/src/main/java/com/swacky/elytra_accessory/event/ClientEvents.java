package com.swacky.elytra_accessory.event;

import com.swacky.elytra_accessory.mixinutils.AccessoryRenderStateData;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ClientEvents {
    private static boolean bootstrapped = false;

    public static void bootstrap() {
        if (!bootstrapped) {
            bootstrapped = true;

            LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register(ClientEvents::onAllowCapeRender);
        } else {
            throw new RuntimeException("Cannot bootstrap " + ClientEvents.class.getName() + " multiple times");
        }
    }

    private static boolean onAllowCapeRender(AvatarRenderState state) {
        AccessoryRenderStateData data = state.getData(AccessoryRenderStateData.KEY);

        if (data != null) {
            for (ItemStack stack : data.items()){
                if (LivingEntity.canGlideUsing(stack, EquipmentSlot.CHEST)) {
                    return false;
                }
            }
        }

        return true;
    }
}
