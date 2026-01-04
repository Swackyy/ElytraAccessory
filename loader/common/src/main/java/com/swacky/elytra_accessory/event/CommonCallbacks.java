package com.swacky.elytra_accessory.event;

import com.swacky.elytra_accessory.common.accessory.GliderBinding;
import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CommonCallbacks {
    public static boolean onElytraFlight(LivingEntity living, boolean shouldTick) {
        if (living instanceof Player player) {
            NonNullList<ItemStack> stacks = AccessoryHelper.getStacks(player);

            for (int i = 0; i < stacks.size(); i++) {
                ItemStack stack = stacks.get(i);

                if (LivingEntity.canGlideUsing(stack, EquipmentSlot.CHEST)) {
                    if (shouldTick) {
                        // Basically copy and paste to ensure vanilla behaviour
                        int j = player.getFallFlyingTicks() + 1;

                        if (j % 10 == 0) {
                            int k = j / 10;

                            if (k % 2 == 0) {
                                stack.hurtAndBreak(1, player, EquipmentSlot.CHEST);

                                // For performance, data component changes are not synchronised with the client by Ohmega,
                                // so we must mark it as changed ourselves
                                AccessoryHelper.getContainer(player).onContentsChanged(i);
                            }
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public static void onPostItemRegistry(Registry<Item> registry) {
        for (Item item : registry) {
            if (item.components().has(DataComponents.GLIDER)) {
                AccessoryHelper.bindAccessory(item, new GliderBinding());
            }
        }
    }
}
