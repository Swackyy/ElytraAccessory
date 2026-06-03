package com.swacky.elytra_accessory.mixin;

import com.swacky.ohmega.api.common.item.AccessoryHelper;
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
abstract class LivingEntityMixin extends Entity implements Attackable, WaypointTransmitter {
    public LivingEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @SuppressWarnings("ConstantValue")
    @Inject(method = "isEquippableInSlot", at = @At(value = "HEAD"), cancellable = true)
    public void isEquippableInSlot(ItemStack itemStack, EquipmentSlot slot, CallbackInfoReturnable<Boolean> cir) {
        if (itemStack.has(DataComponents.GLIDER) && ((Object) this) instanceof Player player) {
            for (ItemStack stack : AccessoryHelper.getData(player).getStacks()) {
                if (stack.has(DataComponents.GLIDER)) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}