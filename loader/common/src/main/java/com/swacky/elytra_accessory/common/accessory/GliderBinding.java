package com.swacky.elytra_accessory.common.accessory;

import com.swacky.ohmega.api.IAccessory;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class GliderBinding implements IAccessory {
    @Override
    public boolean canEquip(@NonNull Player player, @NonNull ItemStack stack) {
        return !player.getItemBySlot(EquipmentSlot.CHEST).has(DataComponents.GLIDER);
    }

    @Override
    public @Nullable Holder<SoundEvent> getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_ELYTRA;
    }
}
