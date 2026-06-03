package com.swacky.elytra_accessory.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.ArmorSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorSlot.class)
public class ArmorSlotMixin {
    @Shadow @Final private LivingEntity owner;

    @Shadow @Final private EquipmentSlot slot;

    @Inject(method = "mayPlace", at = @At(value = "HEAD"), cancellable = true)
    public void mayPlace(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        // Overwrite Forge behaviour if this returns false
        if (!owner.isEquippableInSlot(itemStack, slot)) {
            cir.setReturnValue(false);
        }
    }
}
