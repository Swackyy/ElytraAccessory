package com.swacky.elytra_accessory.mixin;

import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.waypoints.WaypointTransmitter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
abstract class LivingEntityMixin1 extends Entity implements Attackable, WaypointTransmitter {
    public LivingEntityMixin1(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(method = "isEquippableInSlot", at = @At(value = "HEAD"), cancellable = true)
    public void isEquippableInSlot(ItemStack stack, EquipmentSlot slot, CallbackInfoReturnable<Boolean> cir) {
        // IntelliSense has no idea what to do here
        //noinspection ConstantValue
        if (stack.has(DataComponents.GLIDER) && ((Object) this) instanceof Player player) {
            for (ItemStack stack0 : AccessoryHelper.getStacks(player)) {
                if (stack0.has(DataComponents.GLIDER)) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}