package com.swacky.elytra_accessory.common.core.mixin;

import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {
    @Redirect(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    public ItemStack render(AbstractClientPlayer player, EquipmentSlot equipmentSlot) {
        ItemStack stack = player.getItemBySlot(equipmentSlot);
        if (stack.is(Items.ELYTRA)) {
            return stack;
        }
        int slot = AccessoryHelper.getSlotFor(player, Items.ELYTRA);
        if (slot != -1) {
            return AccessoryHelper.getStackInSlot(player, slot);
        }
        return ItemStack.EMPTY;
    }
}
