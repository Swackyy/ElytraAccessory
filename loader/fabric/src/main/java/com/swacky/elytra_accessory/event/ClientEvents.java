package com.swacky.elytra_accessory.event;

import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;

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
        return !ClientCallbacks.shouldPreventCapeRender(state);
    }
}
