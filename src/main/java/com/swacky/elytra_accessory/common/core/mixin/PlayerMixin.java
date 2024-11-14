package com.swacky.elytra_accessory.common.core.mixin;

import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public class PlayerMixin {
    @Redirect(method = "tryToStartFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    public ItemStack tryToStartFallFlying(Player player, EquipmentSlot equipmentSlot) {
        ItemStack stack = player.getItemBySlot(equipmentSlot);
        if (stack.is(Items.ELYTRA)) {
            return stack;
        }
        int slot = AccessoryHelper.getSlotFor(player, Items.ELYTRA);
        if (slot != -1) {
            ItemStack stack0 = AccessoryHelper.getStackInSlot(player, slot);
            if (stack0.is(Items.ELYTRA)) {
                return stack0;
            }
        }
        return ItemStack.EMPTY;
    }
}
