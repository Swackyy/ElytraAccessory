package com.swacky.elytra_accessory.event;

import com.swacky.elytra_accessory.common.ElytraAccessoryCommon;
import com.swacky.ohmega.api.event.AccessoryOverrideTypesEvent;
import com.swacky.ohmega.common.accessorytype.AccessoryType;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

import java.util.Map;

public class CommonEvents {
    private static boolean bootstrapped = false;

    public static void bootstrap() {
        if (!bootstrapped) {
            bootstrapped = true;

            AccessoryOverrideTypesEvent.EVENT.register(CommonEvents::onOverrideAccessoryTypes);
            EntityElytraEvents.CUSTOM.register(CommonEvents::onCustomElytraFlight);
            ServerLifecycleEvents.SERVER_STARTING.register(CommonEvents::onServerStart);
        } else {
            throw new RuntimeException("Cannot bootstrap " + CommonEvents.class.getName() + " multiple times");
        }
    }

    private static boolean onCustomElytraFlight(LivingEntity living, boolean shouldTick) {
        return CommonCallbacks.onElytraFlight(living, shouldTick);
    }

    private static void onOverrideAccessoryTypes(Map<Item, AccessoryType> map) {
        for(Item item : ElytraAccessoryCommon.BOUND_ITEMS) {
            map.put(item, AccessoryType.UTILITY.get());
        }
    }

    private static void onServerStart(MinecraftServer server) {
        CommonCallbacks.bindElytras();
    }
}
