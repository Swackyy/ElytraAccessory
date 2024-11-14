package com.swacky.elytra_accessory.common.core.mixin;

import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot pSlot);

    @Redirect(method = "updateFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    public ItemStack updateFallFlying(LivingEntity living, EquipmentSlot equipmentSlot) {
        ItemStack stack = this.getItemBySlot(EquipmentSlot.CHEST);
        if (stack.is(Items.ELYTRA)) {
            return stack;
        }
        if (living instanceof Player player) {
            int slot = AccessoryHelper.getSlotFor(player, Items.ELYTRA);
            if (slot != -1) {
                ItemStack stack0 = AccessoryHelper.getStackInSlot(player, slot);
                if (stack0.is(Items.ELYTRA)) {
                    return stack0;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    @Inject(method = "getEquipmentSlotForItem", at = @At(value = "HEAD"), cancellable = true)
    public void getEquipmentSlotForItem(ItemStack stack, CallbackInfoReturnable<EquipmentSlot> cir) {
        if (stack.is(Items.ELYTRA)) {
            if ((LivingEntity) (Object) this instanceof Player player && !AccessoryHelper.compatibleWith(player, stack.getItem())) {
                cir.setReturnValue(EquipmentSlot.MAINHAND);
            }
        }
    }
}
