package com.swacky.elytra_accessory.mixin;

import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Equippable.class)
public class EquippableMixin {
    @Inject(
            method = "swapWithEquipmentSlot(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/InteractionResult;",
            at = @At(value = "HEAD"), cancellable = true)
    public void swap(ItemStack stack, Player player, CallbackInfoReturnable<InteractionResult> cir) {
        if (stack.has(DataComponents.GLIDER)) {
            for (ItemStack stack0 : AccessoryHelper.getStacks(player)) {
                if (stack0.has(DataComponents.GLIDER)) {
                    cir.setReturnValue(InteractionResult.PASS);
                }
            }
        }
    }
}
