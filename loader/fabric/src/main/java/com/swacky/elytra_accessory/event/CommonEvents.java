package com.swacky.elytra_accessory.event;

import com.swacky.elytra_accessory.common.ElytraAccessoryCommon;
import com.swacky.ohmega.api.common.accessorytype.AccessoryType;
import com.swacky.ohmega.api.event.AccessoryBindEvent;
import com.swacky.ohmega.api.event.AccessoryOverrideTypesEvent;
import it.unimi.dsi.fastutil.booleans.BooleanObjectPair;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

import java.util.Map;

public class CommonEvents {
    private static boolean bootstrapped = false;

    public static void bootstrap() {
        if (!bootstrapped) {
            bootstrapped = true;

            AccessoryBindEvent.EVENT.register(CommonEvents::onBindAccessories);
            AccessoryOverrideTypesEvent.EVENT.register(CommonEvents::onOverrideAccessoryTypes);
            EntityElytraEvents.CUSTOM.register(CommonEvents::onCustomElytraFlight);
        } else {
            throw new RuntimeException("Cannot bootstrap " + CommonEvents.class.getName() + " multiple times");
        }
    }

    private static void onBindAccessories() {
        CommonCallbacks.bindElytras();
    }

    private static boolean onCustomElytraFlight(LivingEntity living, boolean shouldTick) {
        return CommonCallbacks.onElytraFlight(living, shouldTick);
    }

    private static void onOverrideAccessoryTypes(Map<Item, BooleanObjectPair<AccessoryType>> map) {
        for(Item item : ElytraAccessoryCommon.BOUND_ITEMS) {
            map.put(item, BooleanObjectPair.of(false, AccessoryType.UTILITY.get()));
        }
    }
}
