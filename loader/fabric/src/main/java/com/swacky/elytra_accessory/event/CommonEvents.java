package com.swacky.elytra_accessory.event;

import com.swacky.ohmega.api.AccessoryHelper;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CommonEvents {
    private static boolean bootstrapped = false;

    public static void bootstrap() {
        if (!bootstrapped) {
            bootstrapped = true;

            EntityElytraEvents.CUSTOM.register(CommonEvents::onCustomElytraFlight);
        } else {
            throw new RuntimeException("Cannot bootstrap " + CommonEvents.class.getName() + " multiple times");
        }
    }

    private static boolean onCustomElytraFlight(LivingEntity living, boolean shouldTick) {
        return CommonCallbacks.onElytraFlight(living, shouldTick);
    }
}
