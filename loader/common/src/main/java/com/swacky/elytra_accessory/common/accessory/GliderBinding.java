package com.swacky.elytra_accessory.common.accessory;

import com.swacky.ohmega.api.common.item.EquipContext;
import com.swacky.ohmega.api.common.item.IAccessory;
import com.swacky.ohmega.api.common.item.SoundData;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class GliderBinding implements IAccessory {
    @Override
    public boolean canEquip(@NonNull LivingEntity entity, @NonNull ItemStack stack, @NonNull EquipContext context) {
        return !entity.getItemBySlot(EquipmentSlot.CHEST).has(DataComponents.GLIDER);
    }

    @Override
    public boolean compatibleWith(@NonNull ItemStack stack, @NonNull ItemStack other) {
        return IAccessory.super.compatibleWith(stack, other) && !other.has(DataComponents.GLIDER);
    }

    @Override
    public boolean autoSync(@NonNull ItemStack stack) {
        return true;
    }

    @Override
    public boolean preferVanillaUse(@NonNull ItemStack stack) {
        return false;
    }

    @Override
    public @Nullable SoundData getEquipSound(@NonNull ItemStack stack) {
        return new SoundData(SoundEvents.ARMOR_EQUIP_ELYTRA);
    }
}
