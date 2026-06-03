package com.swacky.elytra_accessory.event;

import com.swacky.elytra_accessory.common.ElytraAccessory;
import com.swacky.elytra_accessory.common.accessory.GliderBinding;
import com.swacky.ohmega.api.common.item.Accessories;
import com.swacky.ohmega.api.common.item.AccessoryHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class CommonCallbacks {
    public static boolean onElytraFlight(LivingEntity living, boolean shouldTick) {
        if (living instanceof Player player) {
            for (ItemStack stack : AccessoryHelper.getData(player).getStacks()) {
                if (LivingEntity.canGlideUsing(stack, EquipmentSlot.CHEST)) {
                    if (shouldTick) {
                        // Basically copy and paste to ensure vanilla behaviour
                        int j = player.getFallFlyingTicks() + 1;

                        if (j % 10 == 0) {
                            int k = j / 10;

                            if (k % 2 == 0) {
                                stack.hurtAndBreak(1, player, EquipmentSlot.CHEST);
                            }
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public static void bindElytras() {
        for (Item item : BuiltInRegistries.ITEM) {
            if (item.components().has(DataComponents.GLIDER)) {
                ElytraAccessory.BOUND_ITEMS.add(item);

                Accessories.bind(item, new GliderBinding());
            }
        }
    }
}
