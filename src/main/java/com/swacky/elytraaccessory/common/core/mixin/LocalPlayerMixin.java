package com.swacky.elytraaccessory.common.core.mixin;

import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    public ItemStack aiStep(LocalPlayer player, EquipmentSlot equipmentSlot) {
        ItemStack stack = player.getItemBySlot(equipmentSlot);
        if (stack.is(Items.ELYTRA)) {
            return stack;
        }
        int slot = AccessoryHelper.getSlotFor(player, Items.ELYTRA);
        if (slot >= 0) {
            ItemStack stack0 = AccessoryHelper.getStackInSlot(player, slot);
            if(stack0.is(Items.ELYTRA)) {
                return stack0;
            }
        }
        return ItemStack.EMPTY;
    }
}
